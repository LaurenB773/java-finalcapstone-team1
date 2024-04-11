package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Schedule;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcScheduleDao implements ScheduleDao {

  private final JdbcTemplate jdbcTemplate;

  public JdbcScheduleDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<Schedule> getAllSchedules() {
    List<Schedule> schedules = new ArrayList<>();
    String sql = "select * from schedules order by class_time asc;";

    try {
      SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
      while (results.next()) {
        schedules.add(mapToRowSchedules(results));
      }
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException(e.getMessage());
    }
    return schedules;
  }

  @Override
  public Schedule getSchedule(int id) {
    String sql = "select * from schedules where schedule_id = ?;";

    try {
      SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
      if (results.next()) {
        return mapToRowSchedules(results);
      }
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException(e.getMessage());
    }
    return null;
  }

  @Override
  public Schedule updateSchedule(Schedule scheduleToUpdate, int id) {
    String sql = "UPDATE schedules SET title = ?, instructor = ?, description = ?, " +
        "class_time = ?, duration_minutes = ? WHERE schedule_id = ?";
    try {
      int numberOfRowsAffected = jdbcTemplate.update(sql, scheduleToUpdate.getTitle(), scheduleToUpdate.getInstructor(),
              scheduleToUpdate.getDescription(), scheduleToUpdate.getClassTime(), scheduleToUpdate.getDuration(), id);

      if (numberOfRowsAffected > 0) {
        return scheduleToUpdate;
      } else {
        throw new DaoException("Cannot find the schedule!");
      }

    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException("Unable to connect to server or database", e);
    } catch (DataIntegrityViolationException e) {
      throw new DaoException("Data integrity violation", e);
    }

  }

  @Override
  public void deleteSchedule(int id) {
    String sql = "delete from schedules where schedule_id = ?;";
    try {
      int rowsAffected = jdbcTemplate.update(sql, id);
      if (rowsAffected == 0) {
        throw new DaoException("Cannot find the user profile!");
      }

    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException("Unable to connect to server or database", e);
    } catch (DataIntegrityViolationException e) {
      throw new DaoException("Data integrity violation", e);
    }

  }


  @Override
  public Schedule createSchedule(Schedule newSchedule) {
    Schedule scheduleToCreate = null;
    String sql = "INSERT INTO schedules (title, instructor, description, class_time, duration_minutes) " +
        "VALUES (?, ?, ?, ?, ?) returning schedule_id;";
    try {
      int id = jdbcTemplate.queryForObject(sql, int.class, newSchedule.getTitle(),
          newSchedule.getInstructor(),
          newSchedule.getDescription(),
          newSchedule.getClassTime(),
          newSchedule.getDuration());
      scheduleToCreate = getSchedule(id);
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException("Unable to connect to server or database", e);
    } catch (DataIntegrityViolationException e) {
      throw new DaoException("Data integrity violation", e);
    }
    return scheduleToCreate;
  }

  private Schedule mapToRowSchedules(SqlRowSet results) {
    Schedule schedule = new Schedule();
    schedule.setInstructor(results.getString("instructor"));
    schedule.setScheduleId(results.getInt("schedule_id"));
    schedule.setTitle(results.getString("title"));
    schedule.setDescription(results.getString("description"));

    LocalDateTime classTime = results.getTimestamp("class_time").toLocalDateTime();
    schedule.setClassTime(classTime);

    schedule.setDuration(results.getInt("duration_minutes"));

    return schedule;
  }
}
package com.techelevator.dao;

import com.techelevator.model.Schedule;
import com.techelevator.model.UserProfile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class JdbcScheduleDao implements ScheduleDao {

  private final JdbcTemplate jdbcTemplate;

  public JdbcScheduleDao(DataSource datasource) {
    jdbcTemplate = new JdbcTemplate(datasource);
  }

  @Override
  public List<Schedule> getAllSchedules() {
    return null;
  }

  @Override
  public Schedule getSchedule(int id) {
    return null;
  }

  @Override
  public UserProfile updateSchedule(int id, Schedule scheduleToUpdate) {
    return null;
  }

  @Override
  public void deleteSchedule(int id) {

  }

  @Override
  public UserProfile createSchedule(Schedule newSchedule) {
    return null;
  }
}
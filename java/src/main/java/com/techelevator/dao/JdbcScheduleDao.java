package com.techelevator.dao;


import com.techelevator.exception.DaoException;
import com.techelevator.model.Equipment;
import com.techelevator.model.Schedule;
import com.techelevator.model.UserProfile;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class JdbcScheduleDao implements ScheduleDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcScheduleDao(DataSource datasource) {
        jdbcTemplate = new JdbcTemplate(datasource);
    }

    @Override
    public List<Schedule> getAllSchedules(){
        List <Schedule> schedules = new ArrayList<>();
        String sql = "select * from schedules";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                schedules.add(mapToRowSchedules(results));
            }
        }catch (CannotGetJdbcConnectionException e){
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
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException(e.getMessage());
        }
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
    private Schedule mapToRowSchedules (SqlRowSet results){
        Schedule schedule = new Schedule();
        schedule.setScheduleId(results.getInt("schedule_id"));
        schedule.setTitle(results.getString("title"));
        schedule.setDescription(results.getString("description"));
        LocalDate classDate = results.getDate("classDate").toLocalDate();
        schedule.setDate(classDate);

        // Retrieve time as LocalTime
        LocalDate startTime = results.getDate("start_time").toLocalDate();
        schedule.setTime(startTime);
        schedule.setDuration(results.getInt("duration_minutes"));
        return schedule;
    }
}
package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import java.util.List;
import java.util.ArrayList;

import com.techelevator.model.Checkin;

@Component
public class JdbcCheckinDao implements CheckinDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcCheckinDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void checkin(int userId) {


        String sql = "insert into checkins (user_id, checkin_time) " +
                "values (?,?) returning checkin_id;";

        try {
            int id  = jdbcTemplate.queryForObject(sql,int.class,userId,LocalDateTime.now());
            if(id == 0){
                throw new DaoException("Id not found");
            }


        }catch(CannotGetJdbcConnectionException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void checkOut(int userId) {
        String sql = "update checkins set checkout_time = ? where checkin_id = (select checkin_id from checkins where user_id = ? and checkout_time is null);";

        try {
            int rows = jdbcTemplate.update(sql,LocalDateTime.now(), userId);
            if (rows == 0) {
                throw new DaoException("Youre stuck here forever");

            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException(e.getMessage());
        }

    }

    @Override
    public boolean isCheckin(int userId) {
        String sql = "select * from checkins where user_id = ? and checkout_time is null;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);

            if (results.next()) {
                return true;
            }
                return false;

        }catch(CannotGetJdbcConnectionException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public List<Checkin> getCheckins(int userId) {
        String sql = "select * from checkins where user_id = ?;";
        List<Checkin> checkins = new ArrayList<>();
        try {
          SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);

          while (results.next()) {
            checkins.add(mapRowToCheckin(results));
          }
        } catch (CannotGetJdbcConnectionException e) {
          throw new DaoException("Cannot connect to database");
        }

        return checkins;
    }

    private Checkin mapRowToCheckin(SqlRowSet results) {
        Checkin checkin = new Checkin();

        checkin.setCheckinId(results.getInt("checkin_id"));
        checkin.setUserId(results.getInt("user_id"));

        checkin.setCheckinTime(results.getTimestamp("checkin_time").toLocalDateTime());
        if (results.getTimestamp("checkout_time") != null) {
          checkin.setCheckoutTime(results.getTimestamp("checkout_time").toLocalDateTime());
        }
        return checkin;

    }
}

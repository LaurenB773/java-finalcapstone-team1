package com.techelevator.dao;

import com.techelevator.model.Checkin;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

public class JdbcCheckinDaoTests extends BaseDaoTests{

    private JdbcCheckinDao sut;

    private final Checkin CHECKIN_1 = new Checkin(1, 1, LocalDateTime.now(), LocalDateTime.now().plusHours(1));
    private final Checkin CHECKIN_2 = new Checkin(3, 2, LocalDateTime.now().minusDays(1), LocalDateTime.now().minusDays(1).plusHours(2));

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcCheckinDao(jdbcTemplate);
    }

    @Test
    public void isCheckin_returns_true_Checkin_exists() {
        sut.checkin(CHECKIN_1.getUserId());

        boolean result = sut.isCheckin(CHECKIN_1.getUserId());

        Assert.assertTrue(result);
    }

    @Test
    public void isCheckin_returns_false_no_Checkin_exists() {
        Assert.assertFalse(sut.isCheckin(CHECKIN_2.getUserId()));

        Assert.assertFalse(sut.isCheckin(CHECKIN_2.getUserId()));
    }

}

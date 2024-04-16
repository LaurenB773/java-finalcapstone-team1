package com.techelevator.dao;

import com.techelevator.model.Exercise;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcExerciseDaoTests extends BaseDaoTests{
    private JdbcExerciseDao sut;

    private final Exercise EXERCISE_1 = new Exercise(1,"bench press",20, 12, 4 , 60);
    private final Exercise EXERCISE_2 = new Exercise(2,"squat",18, 10, 3 , 70);
    private final Exercise EXERCISE_3 = new Exercise(,"squat",18, 10, 3 , 70);

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcExerciseDao(jdbcTemplate);
    }

    @Test
    public void getExercise_returns_all_the_users_exercise(){
        List<Exercise> exercises = sut.getExercises(1);

    }

}

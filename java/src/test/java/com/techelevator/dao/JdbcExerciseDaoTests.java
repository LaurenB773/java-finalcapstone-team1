package com.techelevator.dao;

import com.techelevator.model.Exercise;
import com.techelevator.security.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcExerciseDaoTests extends BaseDaoTests{
    private JdbcExerciseDao sut;
    private final Exercise EXERCISE_1 = new Exercise(1,1,"bench press",20, 12, 4 , 60);
    private final Exercise EXERCISE_2 = new Exercise(2,2,"squat",18, 10, 3 , 70);
    private final Exercise EXERCISE_3 = new Exercise(3,1, "squat",18, 10, 3 , 70);

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcExerciseDao(jdbcTemplate);
    }

    @Test
    public void getExercise_returns_all_the_users_exercise(){
        List<Exercise> exercises = sut.getExercises(1);
        Assert.assertEquals(2,exercises.size());
    }

    @Test
    public void getExerciseById_returns_that_exercise() {
        Exercise exercise = sut.getExerciseById(1);
        Assert.assertEquals(EXERCISE_1.getExerciseName(),exercise.getExerciseName());
        Assert.assertEquals(EXERCISE_1.getExerciseDurationMinutes(),exercise.getExerciseDurationMinutes());
        Assert.assertEquals(EXERCISE_1.getWeightLbs(),exercise.getWeightLbs(),0.0);
    }

    @Test
    public void deleteExercise_deletes_the_exercise() {
       sut.deleteExercise(1);
       Exercise exercise = sut.getExerciseById(1);
       Assert.assertNull(exercise);
    }

}

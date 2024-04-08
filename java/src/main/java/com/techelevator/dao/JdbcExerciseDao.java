package com.techelevator.dao;

import com.techelevator.model.Exercise;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class JdbcExerciseDao implements ExerciseDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcExerciseDao(DataSource datasource) {
        jdbcTemplate = new JdbcTemplate(datasource);
    }
    @Override
    public Exercise startNewExercise() {
        return null;
    }

    @Override
    public List<Exercise> getAllUserExercisesByWorkoutId(int workoutId) {
        return null;
    }

    @Override
    public Exercise getExerciseById(int id) {
        return null;
    }
}

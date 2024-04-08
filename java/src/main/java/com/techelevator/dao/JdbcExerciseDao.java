package com.techelevator.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.techelevator.model.Exercise;

@Component
public class JdbcExerciseDao implements ExerciseDao {

  private JdbcTemplate jdbcTemplate;

  public JdbcExerciseDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Exercise startNewExercise() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'startNewExercise'");
  }

  @Override
  public List<Exercise> getAllUserExercisesByWorkoutId(int workoutId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAllUserExercisesByWorkoutId'");
  }

  @Override
  public Exercise getExerciseById(int id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getExerciseById'");
  }
}

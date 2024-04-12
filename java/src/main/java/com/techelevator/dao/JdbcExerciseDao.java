package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Equipment;
import com.techelevator.model.Exercise;

import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;

@Component
public class JdbcExerciseDao implements ExerciseDao {

  private final JdbcTemplate jdbcTemplate;

  public JdbcExerciseDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Exercise createExercise(CreateExerciseDto dto, int userId) {
    String exerciseSql = "insert into exercises (user_id, exercise_name, exercise_duration_minutes, reps, sets, weight_lbs) values (?, ?, ?, ?, ?, ?) returning exercise_id;";
    String equipmentSql = "update equipments set used_time_minutes = used_time_minutes + ? where equipment_id = ?;";
    String exerciseEquipmentSql = "insert into exercise_equipments (exercise_id, equipment_id) values (?, ?);";

    Exercise exercise = dto.getExercise();

    try {
      int exerciseId = jdbcTemplate.queryForObject(exerciseSql, Integer.class,
       userId,
       exercise.getExerciseName(), exercise.getExerciseDurationMinutes(),
       exercise.getReps(), exercise.getSets(), exercise.getWeightLbs());

      if (exerciseId == 0) {
        throw new DaoException("Unable to create exercise");
      }


      for (int equipmentId : dto.getEquipmentIds()) {
        jdbcTemplate.update(equipmentSql, exercise.getExerciseDurationMinutes(), equipmentId);
        jdbcTemplate.update(exerciseEquipmentSql, exerciseId, equipmentId);
      }

      return getExerciseById(exerciseId);
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException(e.getMessage());
    }
  }

  @Override
  public List<Exercise> getAllUserExercisesByWorkoutId(int workoutId) {
    String sql = "select * from exercises join workout_exercises on exercises.exercise_id = workout_exercises.exercise_id where workout_id = ?;";
    List<Exercise> exercises = new ArrayList<>();

    try {
      SqlRowSet results = jdbcTemplate.queryForRowSet(sql, workoutId);

      while (results.next()) {
        exercises.add(mapToRowExercise(results));
      }

    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException(e.getMessage());
    }

    return exercises;
  }

  @Override
  public Exercise getExerciseById(int exerciseId) {
    String sql = "select * from exercises where exercise_id = ?;";

    try {
      SqlRowSet results = jdbcTemplate.queryForRowSet(sql, exerciseId);

      if (results.next()) {
        return mapToRowExercise(results);
      }
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException("Unable to connect to database");
    }

    return null;
  }

  private Exercise mapToRowExercise(SqlRowSet row) {
    Exercise exercise = new Exercise();

    exercise.setExerciseId(row.getInt("exercise_id"));
    exercise.setExerciseName(row.getString("exercise_name"));
    exercise.setExerciseDurationMinutes(row.getInt("exercise_duration_minutes"));
    exercise.setReps(row.getInt("reps"));
    exercise.setWeightLbs(row.getDouble("weight_lbs"));

    return exercise;
  }
}

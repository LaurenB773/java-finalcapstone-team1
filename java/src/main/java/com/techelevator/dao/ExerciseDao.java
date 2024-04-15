package com.techelevator.dao;

import com.techelevator.model.Exercise;

import java.util.List;

public interface ExerciseDao {
  List<Exercise> getExercises(int userId);
  Exercise createExercise(CreateExerciseDto dto, int userId);
  Exercise getExerciseById(int exerciseId);
}

package com.techelevator.dao;

import com.techelevator.model.Workout;
import java.util.List;

public interface WorkoutDao {
  void startWorkout(int userId);

  void endWorkout(int userId);

  /**
   * The `getWorkouts` function in Java returns a list of all workouts.
   *
   * @return The function `getWorkouts` is returning a list of `Workout` objects.
   */
  List<Workout> getWorkouts();

  Workout getCurrentWorkout(int userId);
}

package com.techelevator.dao;

import com.techelevator.model.Workout;

import java.util.List;

public interface WorkoutDao {

  /**
   * The function `startWorkout` takes in a user profile ID and a workout ID, and
   * initiates a workout
   * for the user.
   * 
   * @param userProfileId The `userProfileId` parameter represents the unique
   *                      identifier of the user
   *                      profile for whom the workout is being started.
   * 
   * @param workoutId     The `workoutId` parameter is an integer that represents
   *                      the unique identifier of
   *                      a specific workout.
   */
  void startWorkout(int userProfileId, int workoutId);

  /**
   * The `endWorkout` function in Java ends a workout for a specific user profile
   * and workout ID.
   * 
   * @param userProfileId The `userProfileId` parameter is an integer that
   *                      represents the unique
   *                      identifier of a user's profile.
   * 
   * @param workoutId     The `workoutId` parameter is an integer that represents
   *                      the unique identifier of
   *                      a specific workout.
   */
  void endWorkout(int userProfileId, int workoutId);

  /**
   * The `getWorkouts` function in Java returns a list of all workouts.
   * 
   * @return The function `getWorkouts` is returning a list of `Workout` objects.
   */
  List<Workout> getWorkouts();

  /**
   * The `getWorkoutById` function in Java returns a specific workout based on the
   * workout ID.
   * 
   * @param id The `id` parameter is an integer that represents the unique
   *           identifier of a specific workout.
   * 
   * @return The function `getWorkoutById` is returning a `Workout` object.
   */
  Workout getWorkoutById(int id);

}

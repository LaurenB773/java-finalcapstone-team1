package com.techelevator.model;

import java.time.LocalDate;

public class Workout {

  private int workoutId;
  private int userProfileId;
  private int exerciseId;
  private LocalDate startTime;
  private LocalDate endTime;

  public Workout() {
  }

  public Workout(int workoutId, int userProfileId, int exerciseId, LocalDate startTime, LocalDate endTime) {
    this.workoutId = workoutId;
    this.userProfileId = userProfileId;
    this.exerciseId = exerciseId;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public int getWorkoutId() {
    return workoutId;
  }

  public void setWorkoutId(int workoutId) {
    this.workoutId = workoutId;
  }

  public int getUserProfileId() {
    return userProfileId;
  }

  public void setUserProfileId(int userProfileId) {
    this.userProfileId = userProfileId;
  }

  public int getExerciseId() {
    return exerciseId;
  }

  public void setExerciseId(int exerciseId) {
    this.exerciseId = exerciseId;
  }

  public LocalDate getStartTime() {
    return startTime;
  }

  public void setStartTime(LocalDate startTime) {
    this.startTime = startTime;
  }

  public LocalDate getEndTime() {
    return endTime;
  }

  public void setEndTime(LocalDate endTime) {
    this.endTime = endTime;
  }
}

package com.techelevator.model;

import java.time.LocalDate;

public class Workout {

  private int workoutId;
  private int userProfileId;
  private LocalDate startTime;
  private LocalDate endTime;

  public Workout() {
  }

  public Workout(int workoutId, int userProfileId, LocalDate startTime, LocalDate endTime) {
    this.workoutId = workoutId;
    this.userProfileId = userProfileId;
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

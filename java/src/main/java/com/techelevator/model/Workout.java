package com.techelevator.model;

import java.time.LocalDateTime;

public class Workout {

  private int workoutId;
  private int userProfileId;
  private LocalDateTime startTime;
  private LocalDateTime endTime;

  public Workout() {
  }

  public Workout(int workoutId, int userProfileId, LocalDateTime startTime, LocalDateTime endTime) {
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

  public LocalDateTime getStartTime() {
    return startTime;
  }

  public void setStartTime(LocalDateTime startTime) {
    this.startTime = startTime;
  }

  public LocalDateTime getEndTime() {
    return endTime;
  }

  public void setEndTime(LocalDateTime endTime) {
    this.endTime = endTime;
  }
}

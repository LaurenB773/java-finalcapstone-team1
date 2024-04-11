package com.techelevator.model;

import java.time.LocalDateTime;

public class Workout {

  private int workoutId;
  private int userId;
  private LocalDateTime startTime;
  private LocalDateTime endTime;

  public Workout() {}

  public Workout(
    int workoutId,
    int userId,
    LocalDateTime startTime,
    LocalDateTime endTime
  ) {
    this.workoutId = workoutId;
    this.userId = userId;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public int getWorkoutId() {
    return workoutId;
  }

  public void setWorkoutId(int workoutId) {
    this.workoutId = workoutId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
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

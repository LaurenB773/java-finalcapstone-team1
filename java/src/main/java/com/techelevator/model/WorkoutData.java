package com.techelevator.model;

import java.util.Date;
import java.util.List;

public class WorkoutData {

  private int workoutDataId;
  private Date startTime;
  private Date endTime;
  private int profileId;
  private List<Exercise> exercises;

  public int getWorkoutDataId() {
    return workoutDataId;
  }

  public void setWorkoutDataId(int workoutDataId) {
    this.workoutDataId = workoutDataId;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  public int getProfileId() {
    return profileId;
  }

  public void setProfileId(int profileId) {
    this.profileId = profileId;
  }

  public WorkoutData() {

  }
  public WorkoutData(int workoutDataId, Date startTime, Date endTime, int profileId, int exerciseId) {
    this.workoutDataId = workoutDataId;
    this.startTime = startTime;
    this.endTime = endTime;
    this.profileId = profileId;
    this.exercises = exercises;
  }
}

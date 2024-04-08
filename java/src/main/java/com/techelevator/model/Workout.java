package com.techelevator.model;

import java.util.Date;
import java.util.List;

public class Workout {

    private int workoutId;
    private Date startTime;
    private Date endTime;
    private int profileId;
    private List<Exercise> exercises;

    public int getWorkoutDataId() {
        return workoutId;
    }

    public void setWorkoutDataId(int workoutDataId) {
        this.workoutId = workoutDataId;
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

    public Workout() {

    }
    public Workout(int workoutDataId, Date startTime, Date endTime, int profileId, int exerciseId) {
        this.workoutId = workoutDataId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.profileId = profileId;
        this.exercises = exercises;
    }
}

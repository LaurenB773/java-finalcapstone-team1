package com.techelevator.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Workout {

    private int workoutId;
    private LocalDate startTime;
    private LocalDate endTime;
    private int profileId;
    private List<Exercise> exercises;

    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutDataId) {
        this.workoutId = workoutDataId;
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

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public Workout() {

    }
    public Workout(int workoutDataId, LocalDate startTime, LocalDate endTime, int profileId, int exerciseId) {
        this.workoutId = workoutDataId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.profileId = profileId;
        this.exercises = exercises;
    }
}

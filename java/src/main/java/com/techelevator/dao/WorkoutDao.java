package com.techelevator.dao;

import com.techelevator.model.Workout;

import java.util.List;

public interface WorkoutDao {
     Workout createWorkout (Workout newWorkout);
     void deleteWorkout(int id);
    Workout updateWorkout(int id, Workout workoutToUpdate);
    Workout getWorkoutById(int id);
    public List<Workout> getAllWorkouts();

}



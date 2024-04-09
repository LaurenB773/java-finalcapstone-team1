package com.techelevator.dao;

import com.techelevator.model.Workout;

import java.util.List;

public interface WorkoutDao {
    /**
     *
     * @return
     */
    List<Workout> getAllWorkouts();

    /**
     *
     * @param id
     * @return Workout
     */
    Workout getWorkoutById(int id);

    /**
     *
     * @param id
     * @param workoutToUpdate
     * @return
     */
    Workout updateWorkout(int id, Workout workoutToUpdate);

    /**
     *
     * @param id
     */
    void deleteWorkout(int id);

    /**
     *
     * @param newWorkout
     * @return
     */
    Workout createWorkout (Workout newWorkout);

}



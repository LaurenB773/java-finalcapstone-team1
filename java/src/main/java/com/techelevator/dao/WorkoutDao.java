package com.techelevator.dao;

import com.techelevator.model.Workout;

import java.util.List;

public interface WorkoutDao {
    /**
     * gets all workouts
     * @return
     */
    List<Workout> getAllWorkouts();

    /**
     * finds workout by id
     * @return workout with given id
     */
    Workout getWorkoutById(int id);

    /**
     * Updates workout get by id and return new workout,
     * update in database
     *
     * @param id
     * @param workoutToUpdate
     * @return updatedWorkout
     */
    Workout updateWorkout(int id, Workout workoutToUpdate);

    /**
     * deletes workout
     *
     * @param id
     */
    void deleteWorkout(int id);

    /**
     * creates workout object when new workout is created based on info
     * inserted by admin
     *
     * @param newWorkout
     * @return createdEquipment
     */
    Workout createWorkout(Workout newWorkout);
}



package com.techelevator.dao;

import com.techelevator.model.Exercise;

import java.util.List;

public interface ExerciseDao {


    /**
     * create new exercise for user
     * @return
     */
    Exercise startNewExercise();

    /**
     * user should be able to see all
     * exercises in workout
     * @return
     */
    List<Exercise> getAllUserExercisesByWorkoutId(int workoutId);

    /**
     * finds exercise by id
     * @return exercise with given id
     */
    Exercise getExerciseById(int id);

    /**
     *
     */


}

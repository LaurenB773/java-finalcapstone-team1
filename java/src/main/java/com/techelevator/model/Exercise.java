package com.techelevator.model;

public class Exercise {

  private int exerciseId;
  private String exerciseName;
  private int exerciseDurationMinutes;
  private int reps;
  private double weight;
  private String equipment;

  public Exercise() {

  }

  public int getExerciseId() {
    return exerciseId;
  }

  public void setExerciseId(int exerciseId) {
    this.exerciseId = exerciseId;
  }

  public String getExerciseName() {
    return exerciseName;
  }

  public void setExerciseName(String exerciseName) {
    this.exerciseName = exerciseName;
  }

  public int getExerciseDurationMinutes() {
    return exerciseDurationMinutes;
  }

  public void setExerciseDurationMinutes(int exerciseDurationMinutes) {
    this.exerciseDurationMinutes = exerciseDurationMinutes;
  }

  public int getReps() {
    return reps;
  }

  public void setReps(int reps) {
    this.reps = reps;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public String getEquipment() {
    return equipment;
  }

  public void setEquipment(String equipment) {
    this.equipment = equipment;
  }

  public Exercise(int exerciseId, String exerciseName, int exerciseDurationMinutes, int reps, double weight,
      String equipment) {
    this.exerciseId = exerciseId;
    this.exerciseName = exerciseName;
    this.exerciseDurationMinutes = exerciseDurationMinutes;
    this.reps = reps;
    this.weight = weight;
    this.equipment = equipment;
  }
}

package com.techelevator.model;

public class Exercise {

  private int exerciseId;
  private int equipmentId;
  private String exerciseName;
  private int exerciseDurationMinutes;
  private int reps;

  private int sets;
  private double weightLbs;
  public Exercise() {

  }

  public Exercise(int exerciseId, int equipmentId, String exerciseName, int exerciseDurationMinutes, int reps,
                  double weightLbs, int sets) {
    this.exerciseId = exerciseId;
    this.equipmentId = equipmentId;
    this.exerciseName = exerciseName;
    this.exerciseDurationMinutes = exerciseDurationMinutes;
    this.reps = reps;
    this.weightLbs = weightLbs;
    this.sets = sets;
  }

  public int getExerciseId() {
    return exerciseId;
  }

  public void setExerciseId(int exerciseId) {
    this.exerciseId = exerciseId;
  }

  public int getEquipmentId() {
    return equipmentId;
  }

  public void setEquipmentId(int equipmentId) {
    this.equipmentId = equipmentId;
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

  public double getWeightLbs() {
    return weightLbs;
  }

  public void setWeightLbs(double weightLbs) {
    this.weightLbs = weightLbs;
  }

  public int getSets() {
    return sets;
  }

  public void setSets(int sets) {
    this.sets = sets;
  }

}

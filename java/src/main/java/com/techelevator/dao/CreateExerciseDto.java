package com.techelevator.dao;

import com.techelevator.model.Exercise;

public class CreateExerciseDto {
  private Exercise exercise;
  private int equipmentId;
  public CreateExerciseDto() {
  }
  public CreateExerciseDto(Exercise exercise, int equipmentId) {
    this.exercise = exercise;
    this.equipmentId = equipmentId;
  }
  public Exercise getExercise() {
    return exercise;
  }
  public void setExercise(Exercise exercise) {
    this.exercise = exercise;
  }
  public int getEquipmentId() {
    return equipmentId;
  }
  public void setEquipmentId(int equipmentId) {
    this.equipmentId = equipmentId;
  }
}

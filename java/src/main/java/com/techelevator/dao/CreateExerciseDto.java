package com.techelevator.dao;

import com.techelevator.model.Exercise;

import java.util.List;

public class CreateExerciseDto {
  private Exercise exercise;
  private List<Integer> equipmentIds;

  public CreateExerciseDto() {

  }

  public CreateExerciseDto(Exercise exercise, List<Integer> equipmentIds) {
    this.exercise = exercise;
    this.equipmentIds = equipmentIds;
  }

  public Exercise getExercise() {
    return exercise;
  }

  public void setExercise(Exercise exercise) {
    this.exercise = exercise;
  }

  public List<Integer> getEquipmentIds() {
    return equipmentIds;
  }

  public void setEquipmentIds(List<Integer> equipmentIds) {
    this.equipmentIds = equipmentIds;
  }

}

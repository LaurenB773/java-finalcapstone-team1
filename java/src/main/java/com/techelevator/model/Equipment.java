package com.techelevator.model;

public class Equipment {

  private int equipmentId;
  private String equipmentName;
  private int userTimeMinutes;

  public Equipment() {

  }

  public Equipment(int equipmentId, String equipmentName, int userTimeMinutes) {
    this.equipmentId = equipmentId;
    this.equipmentName = equipmentName;
    this.userTimeMinutes = userTimeMinutes;
  }

  public int getEquipmentId() {
    return equipmentId;
  }

  public void setEquipmentId(int equipmentId) {
    this.equipmentId = equipmentId;
  }

  public String getEquipmentName() {
    return equipmentName;
  }

  public void setEquipmentName(String equipmentName) {
    this.equipmentName = equipmentName;
  }

  public int getUserTimeMinutes() {
    return userTimeMinutes;
  }

  public void setUserTimeMinutes(int userTimeMinutes) {
    this.userTimeMinutes = userTimeMinutes;
  }
}

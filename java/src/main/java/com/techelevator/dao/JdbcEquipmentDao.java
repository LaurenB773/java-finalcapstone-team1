package com.techelevator.dao;

import com.techelevator.model.Equipment;
import com.techelevator.model.UserProfile;

import java.util.List;

public class JdbcEquipmentDao implements EquipmentDao {

  @Override
  public List<Equipment> getAllEquipment() {
    return null;
  }

  @Override
  public Equipment getEquipmentById(int id) {
    return null;
  }

  @Override
  public UserProfile updateEquipment(int id, Equipment equipmentToUpdate) {
    return null;
  }

  @Override
  public void deleteEquipment(int id) {

  }

  @Override
  public Equipment createEquipment(Equipment newEquipment) {
    return null;
  }
}

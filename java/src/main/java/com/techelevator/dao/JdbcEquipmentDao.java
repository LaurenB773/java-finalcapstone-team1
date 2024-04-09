package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Equipment;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class JdbcEquipmentDao implements EquipmentDao {

  private final JdbcTemplate jdbcTemplate;

  public JdbcEquipmentDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<Equipment> getAllEquipment() {
    List<Equipment> allEquipment = new ArrayList<>();
    String sql = "select * from equipments";

    try {
      SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
      while (results.next()) {
        allEquipment.add(mapToRowEquipment(results));
      }
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException(e.getMessage());
    }

    return allEquipment;
  }

  @Override
  public Equipment getEquipmentById(int id) {
    String sql = "select * from equipments where equipment_id = ?;";

    try {
      SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
      if (results.next()) {
        return mapToRowEquipment(results);
      }
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException(e.getMessage());
    }
    return null;
  }

  @Override
  public void updateEquipment(int id, Equipment equipmentToUpdate) {
    String sql = "update equipments set equipment_name = ?, used_time_minutes = ? where equipment_id = ?";

    try {
      int rowsAffected = jdbcTemplate.update(sql, equipmentToUpdate.getEquipmentName(),
          equipmentToUpdate.getUserTimeMinutes(), id);

      if (rowsAffected != 1) {
        throw new DaoException("Unable to update equipment");
      }
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException(e.getMessage());
    }
  }

  @Override
  public void deleteEquipment(int id) {
    String sql = "delete from equipments where equipment_id = ?";

    try {
      int rowsAffected = jdbcTemplate.update(sql, id);

      if (rowsAffected != 1) {
        throw new DaoException("Unable to delete equipment");
      }
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException(e.getMessage());
    }
  }

  @Override
  public Equipment createEquipment(Equipment newEquipment) {
    String sql = "insert into equipments (equipment_name, used_time_minutes) values (?, ?) returning equipment_id";

    try {
      int newId = jdbcTemplate.queryForObject(sql, Integer.class,
          newEquipment.getEquipmentName(), newEquipment.getUserTimeMinutes());

      newEquipment.setEquipmentId(newId);
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException(e.getMessage());
    }

    return newEquipment;
  }

  private Equipment mapToRowEquipment(SqlRowSet results) {
    Equipment equipment = new Equipment();
    equipment.setEquipmentId(results.getInt("equipment_id"));
    equipment.setEquipmentName(results.getString("equipment_name"));
    equipment.setUserTimeMinutes(results.getInt("used_time_minutes"));
    return equipment;
  }
}

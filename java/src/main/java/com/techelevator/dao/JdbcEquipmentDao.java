package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Equipment;
import com.techelevator.model.UserProfile;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcEquipmentDao implements EquipmentDao {


  private final JdbcTemplate jdbcTemplate;

  public JdbcEquipmentDao(DataSource datasource) {
    jdbcTemplate = new JdbcTemplate(datasource);
  }

  @Override
  public List<Equipment> getAllEquipment() {
    List <Equipment> allEquipment = new ArrayList<>();
    String sql = "select * from equipments";

    try {
      SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
      while (results.next()) {
        allEquipment.add(mapToRowEquipment(results));
      }
    }catch (CannotGetJdbcConnectionException e){
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
    }catch (CannotGetJdbcConnectionException e){
      throw new DaoException(e.getMessage());
    }
    return null;
  }

  @Override
  public Equipment updateEquipment(int id, Equipment equipmentToUpdate) {
    return null;
  }

  @Override
  public void deleteEquipment(int id) {

  }

  @Override
  public Equipment createEquipment(Equipment newEquipment) {
    return null;
  }

  private Equipment mapToRowEquipment(SqlRowSet results){
    Equipment equipment = new Equipment();
    equipment.setEquipmentId(results.getInt("equipment_id"));
    equipment.setEquipmentName(results.getString("equipment_name"));
    equipment.setUserTimeMinutes(results.getInt("used_time_minutes"));
    return equipment;
  }
}

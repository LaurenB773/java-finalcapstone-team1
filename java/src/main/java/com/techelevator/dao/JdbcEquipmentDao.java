package com.techelevator.dao;

import com.techelevator.model.Equipment;
import com.techelevator.model.Schedule;
import com.techelevator.model.UserProfile;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class JdbcEquipmentDao implements EquipmentDao {

    @Override
    public Schedule getAllEquipment(List<Equipment> equipment) {
        return null;
    }

    @Override
    public Schedule getEquipmentById(int id) {
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

package com.techelevator.dao;

import com.techelevator.model.Equipment;
import com.techelevator.model.Schedule;
import com.techelevator.model.UserProfile;

import java.util.List;

public interface EquipmentDao {
    /**
     * get all equipment
     * @return List <Equipment>
     */
    Schedule getAllEquipment(List<Equipment> equipment);

    /**
     * get a equipment by Id
     * @return Equipment
     */
    Schedule getEquipmentById(int id);

    /**
     * Updates equipment get by id and return new equipment,
     * update in database
     * @param id
     * @param equipmentToUpdate
     * @return updatedEquipment
     */

    UserProfile updateEquipment(int id, Equipment equipmentToUpdate);

    /**
     * deletes equipment
     * @param id
     */
    void deleteEquipment(int id);

    /**
     * creates equipment object when new equipment is created based on info
     * inserted by admin
     * @param newEquipment
     * @return createdEquipment
     */
    Equipment createEquipment(Equipment newEquipment);
}


package com.techelevator.dao;

import com.techelevator.model.Equipment;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

public class JdbcEquipmentDaoTests extends BaseDaoTests{

    private JdbcEquipmentDao sut;
    private final Equipment EQUIPMENT_1 = new Equipment(1,"barbell",30);
    private final Equipment EQUIPMENT_2 = new Equipment(2,"dumbbell",50);

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcEquipmentDao(jdbcTemplate);
    }

    @Test
    public void getAllEquipments_gives_list_of_equipments(){
        List<Equipment> allEquipments = sut.getAllEquipment();
        Assert.assertNotNull(allEquipments);
        Assert.assertEquals(2,allEquipments.size());
    }

    @Test
    public void getEquipmentById_returns_equipment_correctly(){
      Equipment equipment1 = sut.getEquipmentById(1);
      Assert.assertEquals(EQUIPMENT_1.getEquipmentName(),equipment1.getEquipmentName());
      Assert.assertEquals(EQUIPMENT_1.getUserTimeMinutes(),equipment1.getUserTimeMinutes());

      Equipment equipment2 = sut.getEquipmentById(2);
        Assert.assertEquals(EQUIPMENT_2.getEquipmentName(),equipment2.getEquipmentName());
        Assert.assertEquals(EQUIPMENT_2.getUserTimeMinutes(),equipment2.getUserTimeMinutes());
    }

    @Test
    public void getEquipmentByID_returns_null_for_invalid_id() {
        Equipment equipment = sut.getEquipmentById(-1);
        Assert.assertNull("Invalid id",equipment);
    }
    @Test
    public void createEquipment_creates_the_equipment() {
        Equipment equipment = new Equipment();
        equipment.setEquipmentName("Bicep curl machine");
        equipment.setUserTimeMinutes(35);
        Equipment createdEquipment = sut.createEquipment(equipment);
        Assert.assertNotNull(createdEquipment);
    }
    @Test
    public void deleteEquipment_deletes_the_equipment() {
        sut.deleteEquipment(1);
        Equipment equipment = sut.getEquipmentById(1);
        Assert.assertNull(equipment);
    }
    @Test
    public void updateEquipment_updates_equipment(){
        Equipment equipment = new Equipment();
        equipment.setEquipmentName("chest press machine");
        equipment.setUserTimeMinutes(20);
        sut.updateEquipment(2,equipment);
        Equipment updatedEquipment = sut.getEquipmentById(2);
        Assert.assertEquals(equipment.getEquipmentName(),updatedEquipment.getEquipmentName());
        Assert.assertEquals(equipment.getUserTimeMinutes(),updatedEquipment.getUserTimeMinutes());
    }


}

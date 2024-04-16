<template>
    <input placeholder="New Equipment Name" type="text" v-model="newEquipment.equipmentName">
    <button @click="createEquipment(this.newEquipment)">Add</button>
    <div class="equipment-item" v-for="item in equipment" :key="item.equipmentId">
        <p>Item Name: {{ item.equipmentName }}</p>
        <p>Time Used: {{ item.userTimeMinutes }} Minutes</p>
        <button class="delete-equipment" @click="removeEquipment(item.equipmentId)">Delete</button>
    </div>
</template>
<script>
import EmployeeService from '../services/EmployeeService';
import EquipmentService from '../services/EquipmentService';

export default {
    data() {
        return {
            equipment: [],
            newEquipment: {
                equipmentName: '',
                userTimeMinutes: 0,
                nextEquipmentId: 0
            },



        }
    },
    mounted() {
        EmployeeService.getAllEquipment().then(response => this.equipment = response.data);
    },
    methods: {
        removeEquipment(id) {
            EquipmentService.deleteEquipment(id);
            window.location.reload();
        },

        createEquipment(equipment) {
            alert('Create Equipment Called');

            let newId = this.findNextId();
            equipment.equipmentId = newId;
            EquipmentService.addEquipment(equipment);
            window.location.reload();
        },
        findNextId() {
            let bigId = 0;
            this.equipment.forEach(element => {
                if (element.equipmentId > bigId) {
                    bigId = element.equipmentId;
                }
            });
            return bigId;
        }

    },
}
</script>
<style> .equipment-item {
     padding-bottom: 1%;
 }

 .delete-equipment {
     font-family: "M PLUS 1 Code", monospace;
     font-optical-sizing: auto;
     font-weight: 400;
     font-style: normal;
     color: var(--color-light-blue);
     background-color: var(--color-medium-grey);
     width: fit-content;
     padding: 5px;
     border: none;
     border-radius: 5px;
 }

 .delete-equipment:hover {
     color: var(--color-red);
 }
</style>
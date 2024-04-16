<template>
    <div class="inputs">
        <input type="text" v-model="search" placeholder="Search for equipment">
        <div>
            <input placeholder="New Equipment Name" type="text" v-model="newEquipment.equipmentName">
            <button class="add-button" @click="createEquipment(this.newEquipment)">Add</button>
        </div>
    </div>
    <div class="equipment-item" v-for="item in filteredEquipment" :key="item.equipmentId">
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
            },
            search: '',


        }
    },
    mounted() {
        EmployeeService.getAllEquipment().then(response => {
            this.equipment = response.data;
            this.equipment.sort((a, b) => { return b.userTimeMinutes - a.userTimeMinutes });
        });
    },

    methods: {
        removeEquipment(id) {
            EquipmentService.deleteEquipment(id);
            window.location.reload();
        },

        createEquipment(equipment) {

            let newId = this.findNextId();
            console.log(newId);
            equipment.equipmentId = newId;
            EquipmentService.addEquipment(equipment);
            window.location.reload();
        },
        findNextId() {
            let bigId = 0;
            this.equipment.forEach(element => {
                if (element.equipmentId > bigId) {
                    bigId = element.equipmentId;
                    console.log(bigId);
                }
            });
            return bigId + 1;
        }

    },
    computed: {
        filteredEquipment() {
            return this.equipment.filter(item => item.equipmentName.toLowerCase().includes(this.search.toLowerCase()));
        }
    }
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

 input {
     margin-bottom: 10px;
     margin-right: 5px;
     outline: none;
     background-color: var(--color-light-blue);
     border-radius: 8px;
     padding: 10px;
 }

 .add-button {
     background-color: var(--color-blue);
     border-radius: 8px;
     padding: 5px;
     font-weight: 600;
     transition: background-color 200ms;
 }

 .add-button:hover {
     background-color: var(--color-blue-o);
     cursor: pointer;
 }

 .inputs {
     display: flex;
     flex-direction: column;
 }
</style>
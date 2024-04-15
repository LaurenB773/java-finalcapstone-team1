<template>
    <div class="equipment-item" v-for="item in equipment" :key="item.equipmentId">
        <p>Item Name: {{ item.equipmentName }}</p>
        <p>Time Used: {{ item.userTimeMinutes }} Minutes</p>
    </div>
</template>
<script>
import EmployeeService from '../services/EmployeeService';

export default {
    data() {
        return {
            equipment: [],

        }
    },
    mounted() {
        EmployeeService.getAllEquipment().then(response => this.equipment = response.data);
    }
}
</script>
<style> .equipment-item {
     padding-bottom: 1%;
 }
</style>
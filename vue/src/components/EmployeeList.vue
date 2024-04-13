<template>
    <div v-for="employee in  employees " :key="employee.userId">
        <p>NAME: {{ employee.firstName }} {{ employee.lastName }}</p>
        <p>EMAIL: {{ employee.email }}</p>
        <p>ID: {{ employee.userId }}</p>
        <button @click="fireEmployee(employee.userId)">Fire</button>
    </div>
</template>
<script>
import EmployeeService from '../services/EmployeeService';
export default {
    data() {
        return {
            employees: [],
        };
    },
    mounted() {
        try {
            EmployeeService.getEmployees().then(response => {
                this.employees = response.data;
            })
        } catch (e) {
            console.log(e);
        }
    },
    methods: {
        fireEmployee(id) {
            EmployeeService.fireEmployee(id);
            window.location.reload();
        }
    }
}
</script>
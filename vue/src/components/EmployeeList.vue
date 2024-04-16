<template>
    <div v-for="employee in  employees " :key="employee.userId">
        <p>NAME: {{ employee.firstName }} {{ employee.lastName }}</p>
        <p>EMAIL: {{ employee.email }}</p>
        <p>ID: {{ employee.userId }}</p>
        <button class="fire" @click="fireEmployee(employee.userId)">Fire</button>
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
                console.log(this.employees)
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

<style>
.fire{
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
.fire:hover {
    color: var(--color-red);
}
</style>
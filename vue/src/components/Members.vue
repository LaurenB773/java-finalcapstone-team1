<template>
    <div v-for="member in members" :key="member.userId">
        <p>First Name: {{ member.firstName }}</p>
        <p>Last Name: {{ member.lastName }}</p>
        <p>Email: {{ member.email }}</p>
        <p>User ID: {{ member.userId }}</p>
        <p>Is User Checked In: {{ amICheckedIn(member.userId) ? 'Checked In' : 'Checked Out' }}</p>
        <div>
            <button @click="checkMemberIn(member.userId)">Check Member
                In</button>
            <button @click="checkMemberOut(member.userId)">Check Member
                Out</button>
            <button>Revoke Membership</button>
            <button>Make Member Employee</button>
        </div>
    </div>
</template>
<script>
import EmployeeService from '../services/EmployeeService';
export default {
    data() {
        return {
            members: [],

        };
    },
    mounted() {
        try {
            EmployeeService.getAllMembers().then(response => {
                this.members = response.data;
            })
        } catch (e) {
            console.log(e);
        }
    },
    methods: {
        checkMemberIn(id) {
            EmployeeService.checkMemberIn(id);

        },
        checkMemberOut(id) {
            EmployeeService.checkMemberOut(id);
        },
        amICheckedIn(id) {
            EmployeeService.isCheckedIn(id).then((response) => {
                console.log(response.data)
                return response.data
            })
        }
    },

}
</script>
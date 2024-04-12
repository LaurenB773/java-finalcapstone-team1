<template>
    <div v-for="member in members" :key="member.userId">
        <p>First Name: {{ member.firstName }}</p>
        <p>Last Name: {{ member.lastName }}</p>
        <p>Email: {{ member.email }}</p>
        <p>User ID: {{ member.userId }}</p>
        <p>Last Visit: {{ member.latest_checkin }}</p>
        <p>Checked In: {{ (amICheckedIn(member.latest_checkout, member.latest_checkin)) }}</p>
        <div>
            <button @click="checkMemberIn(member.userId)"
                v-if="!amICheckedIn(member.latest_checkout, member.latest_checkin)">Check Member In</button>
            <button @click="checkMemberOut(member.userId)"
                v-if="amICheckedIn(member.latest_checkout, member.latest_checkin)">Check
                Member
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
            window.location.reload();
        },
        checkMemberOut(id) {
            EmployeeService.checkMemberOut(id);
            window.location.reload();
        },
        amICheckedIn(checkout, checkin) {
            console.log(checkout < checkin);
            return (checkout < checkin || checkout === null);
        }
    },

}
</script>
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
            <button @click="banMember(member.userId)">Revoke Membership</button>
            <button @click="hireMember(member.userId)" v-if="isOwner()">Make Member Employee</button>
        </div>
    </div>
</template>
<script>
import EmployeeService from '../services/EmployeeService';
import { mapGetters } from 'vuex';
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
        },
        hireMember(id) {
            EmployeeService.makeMemberEmployee(id);
            window.location.reload();
        },
        banMember(id) {
            EmployeeService.banMember(id);
            window.location.reload();
        },
        isOwner() {
            let authorities = [];
            authorities = this.userPermissions;


            if (authorities.some(authority => authority.name === 'ROLE_ADMIN')) {
                return true;
            }
            return false;
        }
    },
    computed: {
        ...mapGetters(['userPermissions'])
    },

}
</script>
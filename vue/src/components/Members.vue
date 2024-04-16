<template>
    <div v-for="member in members" :key="member.userId">
        <p>First Name: {{ member.firstName }}</p>
        <p>Last Name: {{ member.lastName }}</p>
        <p>Email: {{ member.email }}</p>
        <p>User ID: {{ member.userId }}</p>
        <p>Last Visit: {{ timeFormatter(member.latest_checkin) }}</p>
        <p>Checked In: {{ (amICheckedIn(member.latest_checkout, member.latest_checkin)) }}</p>
        <div class="member-buttons">
            <button id="check-member-in-button" @click="checkMemberIn(member.userId)"
                v-if="!amICheckedIn(member.latest_checkout, member.latest_checkin)">Check Member In</button>
            <button id="check-member-out-button" @click="checkMemberOut(member.userId)"
                v-if="amICheckedIn(member.latest_checkout, member.latest_checkin)">Check
                Member
                Out</button>
            <button id="revoke-button" @click="banMember(member.userId)">Revoke Membership</button>
            <button id="hire-button" @click="hireMember(member.userId)" v-if="isOwner()">Make Member Employee</button>
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
                console.log(response.data);
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
        },
        timeFormatter(time) {
            let dateTime = new Date(time);

            let formattedDate = dateTime.toLocaleString("en-US", {
                year: "numeric",
                month: "2-digit",
                day: "2-digit",
                hour: "2-digit",
                minute: "2-digit",
                hour12: true,
            });

            return formattedDate;
        },
    },
    computed: {
        ...mapGetters(['userPermissions'])
    },

}
</script>

<style>
div {
    font-family: "M PLUS 1 Code", monospace;
    font-optical-sizing: auto;
    font-weight: 400;
    font-style: normal;
}

.member-buttons button {
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

.member-buttons button:hover {
    color: var(--color-blue);
    cursor: pointer;
}
</style>
<template>
    <h1>Gym Manager</h1>
    <h2 @click="(isShowing === 'Schedule' ? isShowing = '' : isShowing = 'Schedule')">Manage Schedule</h2>
    <div v-if="isShowing === 'Schedule'">
        <p @click="(isFormShowing === 'Schedule' ? isFormShowing = '' : isFormShowing = 'Schedule')">Create new event</p>
        <form v-if="isFormShowing === 'Schedule'">
            <input placeholder="title" type="text" v-model="newSchedule.title">
            <input placeholder="instructor" type="text" v-model="newSchedule.instructor">
            <!--if theres time make this a dropdown of employees-->
            <input placeholder="description" type="textarea" v-model="newSchedule.description">
            <input placeholder="Hour Of Class" type="datetime-local" v-model="newSchedule.classTime">
            <input placeholder="Duration Minutes" type="number" v-model="newSchedule.duration">
            <button @click="createNewSchedule(newSchedule)">Create!</button>
        </form>
        <p @click="(isFormShowing === 'RemoveSchedule' ? isFormShowing = '' : isFormShowing = 'RemoveSchedule')">See Events
        </p>
        <Schedule v-if="isFormShowing === 'RemoveSchedule'" />
    </div>
    <h2 @click="(isShowing === 'Members' ? isShowing = '' : isShowing = 'Members')">Manage Members</h2>
    <div v-if="isShowing === 'Members'">
        <p>test</p>
        <Members />
    </div>
    <h2 @click="(isShowing === 'Equipment' ? isShowing = '' : isShowing = 'Equipment')">Manage Equipment</h2>
    <div v-if="isShowing === 'Equipment'">
        <p>View Equipment</p>
        <p>Add New Equipment</p>
        <p>Remove Equipment</p>
    </div>
    <h2 @click="(isShowing === 'Employees' ? isShowing = '' : isShowing = 'Employees')" v-if="isOwner()">Manage Employees
    </h2>
    <div v-if="isShowing === 'Employees'">
        <employee-list />
    </div>
</template>
<script>
import Schedule from './Schedule.vue';
import EmployeeService from '../services/EmployeeService';
import Members from './Members.vue'
import EmployeeList from './EmployeeList.vue';
import { mapGetters } from 'vuex';

export default {
    data() {
        return {
            isShowing: '',
            isFormShowing: '',
            newSchedule: {
                title: '',
                instructor: '',
                description: '',
                classTime: null,
                duration: 30,
            }
        }
    },
    components: {
        Schedule,
        Members,
        EmployeeList
    },
    methods: {
        createNewSchedule(newSchedule) {
            EmployeeService.createEvent(newSchedule);
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
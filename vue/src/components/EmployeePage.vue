<template>
    <div class="main">

        <div id="title">
            <h1>GYM MANAGER
                <div id="divider-container">
                    <div class="divider" style="width: 70px"></div>
                    <div class="divider" style="width: 20px"></div>
                </div>
            </h1>
        </div>

        <div class="container">
            <div class="schedule-form">
                <h2 @click="(isShowing === 'Schedule' ? isShowing = '' : isShowing = 'Schedule')">Manage Schedule</h2>

                <p id="p-create-schedule"
                    @click="(isFormShowing === 'Schedule' ? isFormShowing = '' : isFormShowing = 'Schedule')">Create New
                    Event</p>
                <form id="create-schedule" v-if="isFormShowing === 'Schedule'">
                    <input placeholder="Title" type="text" v-model="newSchedule.title">
                    <input placeholder="Instructor" type="text" v-model="newSchedule.instructor">
                    <!--if theres time make this a dropdown of employees-->
                    <input placeholder="Description" type="textarea" v-model="newSchedule.description">
                    <input placeholder="Hour Of Class" type="datetime-local" v-model="newSchedule.classTime">
                    <input placeholder="Duration Minutes" type="number" v-model="newSchedule.duration">
                    <button id="create-schedule-button" @click="createNewSchedule(newSchedule)">Create!</button>
                </form>
                <p id="p-view-schedules"
                    @click="(isFormShowing === 'RemoveSchedule' ? isFormShowing = '' : isFormShowing = 'RemoveSchedule')">
                    See Events
                </p>
                <Schedule v-if="isFormShowing === 'RemoveSchedule'" id="view-schedules" />

            </div>
            <div class="manage-members">
                <h2>Manage Members</h2>
                <div>
                    <Members />
                </div>
            </div>
            <div class="manage-equipment">
                <h2>Manage Equipment</h2>
                <div>
                    <equipment-list />
                </div>
            </div>

            <div class="Employee List">
                <h2 @click="(isShowing === 'Employees' ? isShowing = '' : isShowing = 'Employees')" v-if="isOwner()">
                    Manage
                    Employees
                </h2>

                <employee-list />
            </div>
        </div>
    </div>
</template>
<script>
import Schedule from './Schedule.vue';
import EmployeeService from '../services/EmployeeService';
import Members from './Members.vue'
import EmployeeList from './EmployeeList.vue';
import { mapGetters } from 'vuex';
import EquipmentList from './EquipmentList.vue';

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
        EmployeeList,
        EquipmentList
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
        },

    },
    computed: {
        ...mapGetters(['userPermissions'])
    },
}
</script>

<style>
.main {
    color: var(--color-light-blue);
    display: flex;
    flex-wrap: wrap;
    padding: 15px;
}

.schedule-form {
    max-width: 40%;
}

#title {
    justify-content: center;
    width: 100%;
}

.container {
    border: 2px solid;
    display: flex;
    justify-content: space-between;
    width: 100%;
    padding: 50px;
    background-color: var(--color-medium-grey-o);
    flex-grow: 1;
}

.container h2:hover {
    text-decoration: underline;
    cursor: pointer;
}

#create-schedule {
    flex-wrap: wrap;
    width: 12rem;
    margin-bottom: 20px;
}

#create-schedule input {
    margin-bottom: 10px;
    outline: none;
    background-color: var(--color-light-blue);
    border-radius: 8px;
    padding: 10px;
}

#create-schedule-button {
    width: 100%;
    background-color: var(--color-blue);
    border-radius: 8px;
    padding: 5px;
    font-weight: 600;
    transition: background-color 200ms;
}

#create-schedule-button:hover {
    background-color: var(--color-blue-o);
    cursor: pointer;
}

.event-container:hover {
    cursor: pointer;
}

#divider-container {
    display: flex;
    gap: 10px;
    width: fit-content;
    margin-top: 10px;
    margin-left: 75px;
}

.divider {
    height: 3px;
    border: 1px solid white;
    background-color: white;
    border-radius: 10px;
}

#view-schedules {
    background-color: none;
    margin: 0%;
    padding: 0%;
    align-items: center;
    justify-content: center;
}

#p-create-schedule {
    background-color: var(--color-medium-grey);
    width: fit-content;
    padding: 5px;
    border-radius: 5px;
}

#p-create-schedule:hover {
    color: var(--color-blue);
    cursor: pointer;
}

#p-view-schedules {
    background-color: var(--color-medium-grey);
    width: fit-content;
    padding: 5px;
    border-radius: 5px;
}

#p-view-schedules:hover {
    color: var(--color-blue);
    cursor: pointer;
}
</style>
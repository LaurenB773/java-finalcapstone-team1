<template>
    <main :class="[isEmployeeView ? 'main-container' : '']">
        <form class="form" v-if="isFormShowing === true">
            <p>Edit Event with id: {{ selectedEventId }}</p>
            <input placeholder="title" type="text" v-model="editSchedule.title">
            <input placeholder="instructor" type="text" v-model="editSchedule.instructor">
            <!--if theres time make this a dropdown of employees-->
            <input placeholder="description" type="textarea" v-model="editSchedule.description">
            <p v-if="checkClassTime" style="color: var(--color-red); font-size: 14px; margin: 0;">
                Please select a future date
            </p>
            <input :style="checkClassTime ? 'border: 1px solid red;' : ''" placeholder="Hour Of Class" type="datetime-local"
                v-model="editSchedule.classTime">
            <input placeholder="Duration Minutes" type="number" v-model="editSchedule.duration">
            <p id="confirm-edit" @click="updateEvent()">Confirm Edit</p>
        </form>

        <h1 class="font">Schedule</h1>
        <label style="margin-bottom: 5px;" for="date">Events After Date: </label>
        <input v-model="selectedDate" type="date" name="date" style="cursor:pointer">
        <div :class="[isEmployeeView ? 'event-container-emp' : 'event-container']" v-for="event in filteredSchedule"
            :key="event.scheduleId" @click="this.selectedEventId = event.scheduleId">
            <p class="font event-text">Title: {{ event.title }}</p>
            <p class="font event-text">Instructor: {{ event.instructor }}</p>
            <p class="font event-text">Date: {{ timeFormatter(event.classTime) }}</p>

            <div v-if="selectedEvent(event.scheduleId)">
                <p class="font event-text">Details: {{ event.description }}</p>
                <p class="font event-text">Duration: {{ event.duration }} minutes</p>

                <button class="schedule-edit-button"
                    v-if="selectedEvent(event.scheduleId) && (isEmployee() || isOwner()) && (isEmployeeView)"
                    @click="(isFormShowing = true) && (this.editSchedule = event)">
                    Edit
                </button>
                <button class="schedule-remove-button"
                    v-if="selectedEvent(event.scheduleId) && (isEmployee() || isOwner()) && (isEmployeeView)"
                    @click="removeEvent() && (isEmployeeView)">
                    Remove
                </button>
            </div>

            <button id="sign-up-button" v-if="isMember() && selectedEventId === event.scheduleId"
                @click="amISignedUp(event) ? signOut(selectedEventId) : signUp(selectedEventId)">{{
                    amISignedUp(event) ? 'Cancel' : 'Sign Up' }}</button>
        </div>

    </main>
</template>

<script>
import EmployeeService from "../services/EmployeeService";
import UserService from "../services/UserService";
import { mapGetters } from "vuex";
import getCurrentDate from '../utils/currentDate'

export default {
    data() {
        return {
            events: [],
            selectedEventId: 0,
            editSchedule: {
                id: this.selectedEventId,
                title: '',
                instructor: '',
                description: '',
                classTime: null,
                duration: 30,
            },
            isFormShowing: '',
            signedUp: [],
            selectedDate: null,
            checkClassTime: false
        };
    },
    created() {
        UserService.getSchedule().then(response => this.events = response.data);

    },

    mounted() {
        UserService.getEventsSignedUpFor().then(response => this.signedUp = response.data);
    },
    methods: {
        getCurrentDate,
        selectEvent(id) {
            this.selectedEventId = id;
        },

        selectedEvent(id) {
            return this.selectedEventId === id;
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
        isEmployee() {
            let authorities = [];
            authorities = this.userPermissions;

            if (authorities.some((authority) => authority.name === "ROLE_EMPLOYEE")) {
                return true;
            }
            return false;
        },
        isOwner() {
            let authorities = [];
            authorities = this.userPermissions;

            if (authorities.some((authority) => authority.name === "ROLE_ADMIN")) {
                return true;
            }
            return false;
        },
        isMember() {
            let authorities = [];
            authorities = this.userPermissions;

            if (authorities.some((authority) => authority.name === "ROLE_USER")) {
                return true;
            }
            return false;
        },
        removeEvent() {

            EmployeeService.removeEvent(this.selectedEventId);

            window.location.reload();
        },
        updateEvent() {
            if (this.editSchedule.classTime <= this.getCurrentDate()) {
                this.checkClassTime = true;
            } else {
                this.checkClassTime = false;
            }

            if (!this.checkClassTime) {
                EmployeeService.updateEvent(this.editSchedule, this.selectedEventId);
                window.location.reload();
            }
        },
        signUp(id) {
            UserService.signUpForEvent(id);
            window.location.reload();
        },
        amISignedUp(event) {
            return this.signedUp.some(steve => { return steve.scheduleId === event.scheduleId });
        },
        signOut(id) {
            UserService.leaveEvent(id);
            window.location.reload();
        }

    },
    computed: {
        ...mapGetters(["userPermissions"]),
        isEmployeeView() {
            return this.$route.name === 'employee'
        },
        filteredSchedule() {
            if (this.selectedDate === null) {
                return this.events;
            } else {
                return this.events.filter(event => event.classTime > this.selectedDate);
            }
        }
    },
};
</script>

<style scoped>
main {
    display: flex;
    flex-direction: column;
    align-items: center;
}

h1 {
    font-size: 50px;
}

.event-container {
    background-color: var(--color-light-blue);
    border-radius: 10px;
    margin: 10px;
    padding: 10px;
    width: 50%;
}

.event-container:hover {
    background-color: var(--color-light-blue-o);
}

.event-container-emp {
    background-color: var(--color-light-blue);
    width: 100%;
    border-radius: 5px;
    padding: 10px;
    margin-bottom: 15px;
}

.event-container-emp:hover {
    background-color: var(--color-light-blue-o);
    cursor: pointer;
}

.main-container {
    display: flex;
    flex-direction: column;
    justify-content: space-evenly;
    flex-shrink: 0;
}

.event-text {
    color: black;
    text-align: start;
}

.schedule-edit-button {
    background-color: var(--color-blue);
    border-radius: 8px;
    border: none;
    padding: 5px;
    font-weight: 600;
    transition: background-color 200ms;
    margin-right: 3px;
}

.schedule-edit-button {
    background-color: var(--color-blue-o);
    cursor: pointer;
}

.schedule-remove-button {
    background-color: var(--color-blue);
    border-radius: 8px;
    border: none;
    padding: 5px;
    font-weight: 600;
    transition: background-color 200ms;
}

.schedule-remove-button {
    background-color: var(--color-blue-o);
    cursor: pointer;
}

#confirm-edit {
    background-color: var(--color-medium-grey);
    width: fit-content;
    margin: auto;
    padding: 5px;
    border-radius: 5px;
}

#confirm-edit:hover {
    color: var(--color-blue);
    cursor: pointer;
}
#sign-up-button{
    background-color: var(--color-blue);
    border: none;
    border-radius: 8px;
    padding: 5px;
    font-weight: 600;
    transition: background-color 200ms;
}
#sign-up-button:hover{
    background-color: var(--color-blue-o);
    cursor: pointer;
}
</style>

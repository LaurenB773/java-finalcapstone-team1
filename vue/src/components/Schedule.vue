<template>
    <main>
        <form v-if="isFormShowing === true">
            <p>Edit Event with id: {{ selectedEventId }}</p>
            <input placeholder="title" type="text" v-model="editSchedule.title">
            <input placeholder="instructor" type="text" v-model="editSchedule.instructor">
            <!--if theres time make this a dropdown of employees-->
            <input placeholder="description" type="textarea" v-model="editSchedule.description">
            <input placeholder="Hour Of Class" type="datetime-local" v-model="editSchedule.classTime">
            <input placeholder="Duration Minutes" type="number" v-model="editSchedule.duration">
            <p @click="updateEvent()">Confirm Edit</p>
        </form>

        <h1 class="font">Today's Events</h1>
        <div class="event-container" v-for="event in events" :key="event.scheduleId" @click="selectEvent(event.scheduleId)">
            <p class="font event-text">Title: {{ event.title }}</p>
            <p class="font event-text">Instructor: {{ event.instructor }}</p>
            <p class="font event-text">Date: {{ timeFormatter(event.classTime) }}</p>

            <div v-if="selectedEvent(event.scheduleId)">
                <p class="font event-text">Details: {{ event.description }}</p>
                <p class="font event-text">Duration: {{ event.duration }} minutes</p>

                <button v-if="selectedEvent(event.scheduleId) && (isEmployee() || isOwner())"
                    @click="(isFormShowing = true) && (this.editSchedule = event)">
                    Edit
                </button>
                <button v-if="selectedEvent(event.scheduleId) && (isEmployee() || isOwner())" @click="removeEvent()">
                    Remove
                </button>
            </div>

            <button v-if="isMember() && selectedEventId === event.scheduleId"
                @click="amISignedUp(event) ? signOut(selectedEventId) : signUp(selectedEventId)">{{
                    amISignedUp(event) ? 'Cancel' : 'Sign Up' }}</button>
        </div>

    </main>
</template>

<script>
import EmployeeService from "../services/EmployeeService";
import UserService from "../services/UserService";
import { mapGetters } from "vuex";

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

        };
    },
    created() {
        UserService.getSchedule().then(response => this.events = response.data);

    },

    mounted() {
        UserService.getEventsSignedUpFor().then(response => this.signedUp = response.data);
    },
    methods: {
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
            EmployeeService.updateEvent(this.editSchedule, this.selectedEventId);
            window.location.reload();
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

.event-text {
    color: black;
    text-align: start;
}
</style>

<template>
    <div>
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
        <h1>Today's Events</h1>
        <div class="event-container" v-for="event in    events   " :key="event.scheduleId"
            @click="selectEvent(event.scheduleId)" style="cursor: pointer">
            <p>Title: {{ event.title }}</p>
            <p>Instructor: {{ event.instructor }}</p>
            <p>Date: {{ timeFormatter(event.classTime) }}</p>
            <p v-if="selectedEventId === event.scheduleId">
                Details: {{ event.description }}
            </p>
            <p v-if="selectedEventId === event.scheduleId">
                Duration: {{ event.duration }} minutes
            </p>
            <button v-if="selectedEventId === event.scheduleId && (isEmployee() || isOwner())"
                @click="(isFormShowing = true) && (this.editSchedule = event)">
                Edit
            </button>
            <button v-if="selectedEventId === event.scheduleId && (isEmployee() || isOwner())" @click="removeEvent()">
                Remove
            </button>
            <button v-if="isMember() && selectedEventId === event.scheduleId">Sign Up!(TODO)</button>

        </div>

    </div>
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
        };
    },
    mounted() {
        UserService.getAllSchedule().then(
            (response) => (this.events = response.data)
        );
    },
    methods: {
        selectEvent(id) {
            this.selectedEventId = id;
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

    },
    computed: {
        ...mapGetters(["userPermissions"]),
    },
};
</script>

<style>
.event-container:hover {
    background-color: aliceblue;
}
</style>

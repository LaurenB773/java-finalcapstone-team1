<template>
<div>
    <p>Today's Events</p>
    <div class="event-container" v-for="event in events" :key="event.scheduleId" @click="selectEvent(event.scheduleId)" style="cursor:pointer;">
    <p>Title: {{ event.title }}</p>
    <p>Instructor: {{ event.instructor }}</p>
    <p>Date: {{ timeFormatter(event.classTime) }}</p>
    <p v-if="selectedEventId === event.scheduleId">Details: {{event.description}}</p>
    <p v-if="selectedEventId === event.scheduleId" >Duration: {{ event.duration }} minutes</p>
    </div>
</div>
</template>

<script>
import UserService from '../services/UserService';
export default {
    data() {
        return {
            events: [],
            selectedEventId: 0
        }
    },
    mounted() {
        UserService.getAllSchedule().then(response => this.events = response.data);
    },
    methods: {
        selectEvent(id) {
            if (id === this.selectedEventId) {
                this.selectedEventId = 0;
            } else {
                this.selectedEventId = id;
            }
        },
        timeFormatter(time) {
            let dateTime = new Date(time);

            let formattedDate = dateTime.toLocaleString('en-US', { 
                year: 'numeric',
                month: '2-digit',
                day: '2-digit',
                hour: '2-digit',
                minute: '2-digit',
                hour12: true
            });

            return formattedDate;
        }
    }
}

</script>

<style>
.event-container:hover {
    background-color: aliceblue;
}
</style>
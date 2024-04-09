<template>
<p>Schedule goes here!</p>
<div v-for="event in events" :key="event.schedule_id">
    <h1>might b working</h1>
</div>
</template>
<script>
import UserService from '../services/UserService';

export default {
 
    data() {
        return {
            events: [],
        };
    },

    mounted() {
        UserService.getAllSchedule().then(response => {
            this.events = response.data;
        }).catch(error => {
            console.error('Cant fetch schedule', error);
        })
    }
}
</script>
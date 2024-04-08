<template>
    <div>
        <h1>Welcome (put username here)</h1>

        <div class="schedule">
            <h1>Scheduled Events</h1>
            <scheduled-event v-for="event in schedules" :key="event.schedule_id"/> 
        </div>
    </div>
</template>

<script>
import ScheduledEvent from '../components/ScheduledEvent.vue';

export default {
    props: ['schedules'],

    components: {
        ScheduledEvent
    }
}
</script>
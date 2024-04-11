<template>
    <h1>Gym Manager</h1>
    <h2 @click="(isShowing === 'Schedule' ? isShowing = '' : isShowing = 'Schedule')">Manage Schedule</h2>
    <div v-if="isShowing === 'Schedule'">
        <p>Add new event to schedule</p>
        <p>Remove event from schedule</p>
        <p>Edit event on schedule</p>
        <p>See all events</p>
    </div>
    <h2  @click="(isShowing === 'Members' ? isShowing = '' : isShowing = 'Members')">Manage Members</h2>
    <div v-if="isShowing === 'Members'">
        <p>Edit Member Permissions</p>
        <p>Remove Member</p>
        <p>Check Member In</p>
    </div>
    <h2 @click="(isShowing === 'Equipment' ? isShowing = '' : isShowing = 'Equipment')">Manage Equipment</h2>
    <div v-if="isShowing === 'Equipment'">
        <p>View Equipment</p>
        <p>Add New Equipment</p>
        <p>Remove Equipment</p>
    </div>
    <Schedule v-if="false"/>
</template>
<script>
import Schedule from './Schedule.vue';

export default {
data() {
    return {
        isShowing: '',
    }
},
components: {
    Schedule
},
methods: {
   
}
}
</script>
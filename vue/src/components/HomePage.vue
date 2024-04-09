<template>
    <div>
        <h1>Welcome (put username here)</h1>

        <button @click="viewSchedule()">Schedule</button>
    </div>
</template>

<script>
import UserService from '../services/UserService'
export default {

   
    components: {
        
    },
    methods: {
        viewSchedule() {
            this.$router.push('/gym/schedule');
        }
    }
}
</script>
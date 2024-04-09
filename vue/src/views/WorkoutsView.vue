<template>
    <h1>Users workouts go here</h1>
    <workout-list />
    <workout-detail />
</template>

<script>
import WorkoutList from '../components/WorkoutList.vue';
import WorkoutDetail from '../components/WorkoutDetail.vue';

export default {
    components: {
        WorkoutList,
        WorkoutDetail
    }
}
</script>
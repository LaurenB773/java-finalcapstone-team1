<template>
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
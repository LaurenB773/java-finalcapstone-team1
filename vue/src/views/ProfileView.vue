<template>

    <h1>firstname lastname</h1>
    <img src="" alt="Profile-Picture"> <!--TODO-->
    <p>goals</p>
    <router-link v-bind:to="{name: 'workouts' }"> See Workouts </router-link>
    

</template>

<script>
export default {
    props: ['users'],
};
</script>

<style>
</style>
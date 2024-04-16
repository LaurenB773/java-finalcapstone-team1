<template>
    <schedule />
    <div class="welcome">
    <h1>Welcome to Fitness Elevator!</h1>
    <p id="description">
        At Fitness Elevator, we're more than just a gym; we're dedicated to elevating each individual to their highest potential. Established on April 5th, 2024, Fitness Elevator boasts a rich history of providing gym owners 
        and members with cutting-edge technology to achieve their fitness goals.
    </p>
    <ol>What We Offer:</ol>
    <ol>
     <ul>- Equipment Usage Tracking: Gain valuable insights into which equipment is most and least utilized in your gym.</ul>
     <ul>- Streamlined Employee and Member Management: Effortlessly manage your staff and gym members with our user-friendly platform.</ul>
     <ul>- Simplified Communication with Members: Stay connected with your members through easy-to-use gym updating tools.</ul>
     <ul>- Workout and Goal Tracking: Keeps members motivated by allowing them to set goals and see progress</ul>
    </ol>
</div>
</template>

<script>
import Schedule from '../components/Schedule.vue';
export default {


    components: {
        Schedule
    },
    methods: {

    }
}
</script>

<style>
.welcome {
    font-family: "M PLUS 1 Code", monospace;
    font-optical-sizing: auto;
    font-weight: 400;
    font-style: normal;
    border: 2px solid white;
    background-color: rgba(0, 0, 0, 0.5);
    margin: 15px;
    color: var(--color-light-blue);
}
h1 {
    margin-left: 10px;
}
#description {
    margin-left: 10px;
}

</style>

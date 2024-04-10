<template>
	<h1>{{ this.userProfile.firstName }} {{ this.userProfile.lastName }}</h1>
	<img
		src=""
		alt="Profile-Picture"
	/>
	<!--TODO-->
	<p>Your goals are: {{ this.userProfile.goal }}</p>
	<router-link v-bind:to="{ name: 'workouts' }"> See Workouts </router-link>
	<router-link v-bind:to="{ name: 'newWorkout' }">Start New Workout</router-link>
	<button @click="checkInOrOut">{{ isCheckedIn ? 'check out' : 'check in' }}</button>
	<!-- <button v-else @click="checkInOrOut">Check Out</button>	 -->
</template>

<script>
import { mapState } from 'vuex'

import UserService from '../services/UserService'
import axios from 'axios'

export default {
	data() {
		return {
			userProfile: {
				userId: 0,
				firstName: '',
				lastName: '',
				email: '',
				profilePicture: '',
				goal: '',
			},
			isCheckedIn: false,

		}
	},
	mounted() {
		UserService.getProfile().then(res => this.userProfile = res.data)
		UserService.getLastCheckin().then(response => this.isCheckedIn = response.data)
	},
	computed: {
		...mapState(['user']),
	},
	methods: {
		checkInOrOut() {
			if (this.isCheckedIn) {
				UserService.checkOut();
				this.isCheckedIn = false;
			} else {
				UserService.checkIn();
				this.isCheckedIn = true;
			} 

		}
		
	}
}
</script>

<style></style>

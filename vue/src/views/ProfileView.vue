<template>
	<div class="profile-container">
		<h2>Name: {{ this.userProfile.firstName }} {{ this.userProfile.lastName }}</h2>

		<img
			:src="this.userProfile.profilePicture"
			alt="Profile-Picture"
		/>

		<h2>Goals: {{ this.userProfile.goal }}</h2>

		<h2>Average Gym Time: {{ calculateAverageTimeSpent() }} minutes</h2>
		<h2 v-if="this.checkins[this.checkins.length - 1]">Last Visit: {{ formatTimestamp(this.checkins[this.checkins.length - 1].checkinTime) }}</h2>

		<div class="options-container">
			<button @click="checkInOrOut">
				{{ isCheckedIn ? 'Check Out' : 'Check In' }}
			</button>

			<router-link
				v-bind:to="{ name: 'workouts' }"
				@click="searchWorkouts"
			>
				<button>Search Exercises</button>
			</router-link>

			<button
				:class="isShowingPreviousExercises && 'selected'"
				@click="showPreviousExercises"
			>
				Previous Exercises
			</button>

			<button
				:class="isShowingSchedule && 'selected'"
				@click="showSchedule"
			>
				My Schedule
			</button>
		</div>

		<PreviousExercises v-if="isShowingPreviousExercises" />

		<MySchedule v-if="isShowingSchedule" />
	</div>
</template>

<script>
import { mapState } from 'vuex'
import UserService from '../services/UserService'
import { RouterLink } from 'vue-router'
import PreviousExercises from '../components/PreviousExercises.vue'
import MySchedule from '../components/MySchedule.vue'
import { formatTimestamp } from '../utils/formatter'

export default {
	components: {
		RouterLink,
		PreviousExercises,
		MySchedule,
	},
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
			checkins: [],
			isCheckedIn: false,
			isShowingPreviousExercises: true,
			isShowingSchedule: false,
		}
	},

	mounted() {
		UserService.getProfile().then(res => (this.userProfile = res.data))
		UserService.getLastCheckin().then(response => (this.isCheckedIn = response.data))
		UserService.getCheckins().then(response => (this.checkins = response.data))
	},

	computed: {
		...mapState(['user']),
	},

	methods: {
    formatTimestamp,
		checkInOrOut() {
			if (this.isCheckedIn) {
				UserService.checkOut()
				this.isCheckedIn = false
			} else {
				UserService.checkIn()
				this.isCheckedIn = true
			}
		},

		showPreviousExercises() {
			this.isShowingPreviousExercises = !this.isShowingPreviousExercises
			this.isShowingSchedule = false
		},

		showSchedule() {
			this.isShowingSchedule = !this.isShowingSchedule
			this.isShowingPreviousExercises = false
		},

		calculateAverageTimeSpent() {
			let totalMinutes = 0

			// Calculate total time spent in milliseconds
			this.checkins.filter(checkin => checkin.checkoutTime !== null).forEach(checkin => {
				const checkinTime = new Date(checkin.checkinTime)
				const checkoutTime = new Date(checkin.checkoutTime)
        const timeDifference =  checkoutTime - checkinTime
        totalMinutes += timeDifference / (1000 * 60) // converting milliseconds to minutes
			})

			// Calculate average time spent in minutes
			const averageMinutes = totalMinutes / this.checkins.length

			return averageMinutes ? averageMinutes.toFixed(2) : 0
		},

    
	},
}
</script>

<style scoped>
.profile-container {
	color: var(--color-light-blue);
	width: 50%;
	margin: auto;
	margin-top: 50px;

	font-family: 'M PLUS 1p', monospace;
}

.profile-container h2 {
	font-weight: 400;
}

.options-container {
	display: flex;
	gap: 20px;
}

button {
	background-color: var(--color-grey);
	color: var(--color-light-blue);
	border: none;
	border-radius: 5px;
	padding: 10px;
	cursor: pointer;
	transition: background-color 200ms;

	font-family: 'M PLUS 1p', monospace;
}

button:hover {
	background-color: var(--color-light-blue);
	color: var(--color-grey);
}

button.selected {
	background-color: var(--color-light-blue);
	color: var(--color-grey);
}
</style>

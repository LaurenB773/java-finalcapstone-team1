<template>
	<div v-if="!isShowingProfile" class="edit-profile-container">
		<h1>Edit Profile</h1>

		<svg @click="isShowingProfile = true; isShowingSettings = false" xmlns="http://www.w3.org/2000/svg" width="24"
			height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
			stroke-linejoin="round" class="feather feather-arrow-left go-back-icon">
			<line x1="19" y1="12" x2="5" y2="12"></line>
			<polyline points="12 19 5 12 12 5"></polyline>
		</svg>

		<form @submit.prevent="updateProfile">
			<div class="form-input-group">
				<label for="email">Email:</label>
				<input placeholder="Email" type="text" name="email" id="email" v-model="editProfile.email" />
			</div>

			<div class="form-input-group">
				<label for="goalSelector">Gym Goal:</label>
				<select id="goalSelector" v-model="editProfile.goal">
					<option value="Weight Loss">Weight Loss</option>
					<option value="Strength/Muscle Building">Strength/Muscle Building</option>
					<option value="Aesthetic Body Building">Aesthetic Body Building</option>
					<option value="Health & Enjoyment">Health & Enjoyment</option>
					<option value="Not Sure">Not Sure</option>
				</select>
			</div>

			<div v-if="user.profilePicture" class="form-input-group">
				Preview:
				<img :src="user.profilePicture" alt="profile-picture" />
			</div>

			<div class="form-input-group">
				<label for="first-name">First Name:</label>
				<input placeholder="First name" type="text" id="first-name" v-model="editProfile.firstName" />
			</div>
			<div class="form-input-group">
				<label for="last-name">Last Name:</label>
				<input placeholder="Last name" type="text" id="last-name" v-model="editProfile.lastName" />
			</div>

			<div class="form-input-group">
				<label for="photo">Profile Picture:</label>
				<button id="photo" @click="uploadFile" class="upload-button" style="cursor: pointer; color: black;"
					:style="editProfile.profilePicture ? 'background-color: var(--color-green);' : ''">{{ editProfile.profilePicture ?
						'Successfully Uploaded!' : 'Upload Image...' }}</button>
			</div>

			<button class="create-user-button" type="submit">
				Edit Account
			</button>
		</form>
	</div>

	<div class="profile-container" v-if="isShowingProfile">
		<h2>Name: {{ userProfile.firstName }} {{ userProfile.lastName }}</h2>

		<svg @click="isShowingSettings = !isShowingSettings" xmlns="http://www.w3.org/2000/svg" width="24" height="24"
			viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
			stroke-linejoin="round" class="feather feather-settings settings-icon">
			<circle cx="12" cy="12" r="3"></circle>
			<path
				d="M19.4 15a1.65 1.65 0 0 0 .33 1.82l.06.06a2 2 0 0 1 0 2.83 2 2 0 0 1-2.83 0l-.06-.06a1.65 1.65 0 0 0-1.82-.33 1.65 1.65 0 0 0-1 1.51V21a2 2 0 0 1-2 2 2 2 0 0 1-2-2v-.09A1.65 1.65 0 0 0 9 19.4a1.65 1.65 0 0 0-1.82.33l-.06.06a2 2 0 0 1-2.83 0 2 2 0 0 1 0-2.83l.06-.06a1.65 1.65 0 0 0 .33-1.82 1.65 1.65 0 0 0-1.51-1H3a2 2 0 0 1-2-2 2 2 0 0 1 2-2h.09A1.65 1.65 0 0 0 4.6 9a1.65 1.65 0 0 0-.33-1.82l-.06-.06a2 2 0 0 1 0-2.83 2 2 0 0 1 2.83 0l.06.06a1.65 1.65 0 0 0 1.82.33H9a1.65 1.65 0 0 0 1-1.51V3a2 2 0 0 1 2-2 2 2 0 0 1 2 2v.09a1.65 1.65 0 0 0 1 1.51 1.65 1.65 0 0 0 1.82-.33l.06-.06a2 2 0 0 1 2.83 0 2 2 0 0 1 0 2.83l-.06.06a1.65 1.65 0 0 0-.33 1.82V9a1.65 1.65 0 0 0 1.51 1H21a2 2 0 0 1 2 2 2 2 0 0 1-2 2h-.09a1.65 1.65 0 0 0-1.51 1z">
			</path>
		</svg>

		<div class="settings-container" v-if="isShowingSettings">
			<div @click="isShowingProfile = false" class="settings-option">Edit Profile</div>
			<hr>
			<div @click="deleteProfile" class="settings-option">Delete Profile</div>
		</div>

		<img :src="userProfile.profilePicture" alt="Profile-Picture" />

		<h2>Goals: {{ userProfile.goal }}</h2>

		<h2>Average Gym Time: {{ calculateAverageTimeSpent() }} minutes</h2>
		<h2 v-if="checkins[checkins.length - 1]">Last Visit: {{ formatTimestamp(checkins[checkins.length - 1].checkinTime)
		}}</h2>

		<div class="options-container">
			<button @click="checkInOrOut">
				{{ isCheckedIn ? 'Check Out' : 'Check In' }}
			</button>

			<router-link v-bind:to="{ name: 'workouts' }" @click="searchWorkouts">
				<button>Search Exercises</button>
			</router-link>

			<button :class="isShowingPreviousExercises && 'selected'" @click="showPreviousExercises">
				Previous Exercises
			</button>

			<button :class="isShowingSchedule && 'selected'" @click="showSchedule">
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
// DO NOT REMOVE THIS ESLINT DISABLE LINE BELOW
// eslint-disable-next-line import/no-unresolved
import { openUploadModal } from "@bytescale/upload-widget-vue";

const options = {
	apiKey: "free", // Get API key: https://www.bytescale.com/get-started
	maxFileCount: 1
};

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

			editProfile: {},
			checkins: [],
			isCheckedIn: false,
			isShowingPreviousExercises: true,
			isShowingSchedule: false,
			isShowingSettings: false,
			isShowingProfile: true
		}
	},

	mounted() {
		UserService.getProfile().then(response => {
			this.userProfile = response.data
			this.editProfile = response.data
		})
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
				const timeDifference = checkoutTime - checkinTime
				totalMinutes += timeDifference / (1000 * 60) // converting milliseconds to minutes
			})

			// Calculate average time spent in minutes
			const averageMinutes = totalMinutes / this.checkins.length

			return averageMinutes ? averageMinutes.toFixed(2) : 0
		},

		updateProfile() {
			UserService.editProfile(this.editProfile)
			this.isShowingProfile = true
			this.isShowingSettings = false;
		},

		deleteProfile() {
			UserService.deleteProfile()
			this.$router.push('/logout')
		},

		uploadFile(event) {
			openUploadModal({
				event,
				options,
				onComplete: files => {
					if (files.length === 0) {
						alert('No files selected.')
					} else {
						this.editProfile.profilePicture = files.map(f => f.fileUrl).join('\n')
					}
				},
			})
		},

	},
}
</script>

<style scoped>
.profile-container {
	background-color: var(--color-medium-grey-o);
	border: 2px solid white;
	color: var(--color-light-blue);
	width: 50%;
	margin: auto;
	margin-top: 50px;
	padding: 10px;

	font-family: 'M PLUS 1p', monospace;

	position: relative
}

.settings-icon {
	position: absolute;
	top: 10px;
	right: 10px;

	cursor: pointer;
}

.settings-icon:hover {
	color: var(--color-grey);
}

.settings-container {
	position: absolute;
	top: 20px;
	right: 20px;

	background-color: var(--color-grey);
	border: 1px solid transparent;
	border-radius: 5px;
	padding: 10px;
}

.profile-container h2 {
	font-weight: 400;
}

.options-container {
	display: flex;
	gap: 20px;
}

.settings-option {
	cursor: pointer;
}

.settings-option:hover {
	color: var(--color-blue);
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

.edit-profile-container {
	display: flex;
	flex-direction: column;
	align-items: center;
	width: 50%;
	margin: auto;
	margin-top: 20px;

	color: var(--color-light-blue);

	position: relative;
}

.go-back-icon {
	position: absolute;
	top: 0;
	left: 0;

	cursor: pointer;
}

.go-back-icon:hover {
	color: var(--color-grey);
}

.form-input-group {
	margin-bottom: 1rem;
	color: var(--color-light-blue);
	display: flex;
	justify-content: space-between;
	padding: 12px;
	align-items: center;
}

.form-input-group input {
	outline: none;
	background-color: hsl(204, 5%, 79%);
	border-radius: 8px;
	padding: 10px;
}

label {
	margin-right: 0.5rem;
}

form {
	display: flex;
	flex-direction: column;
	justify-content: space-between;
}

.create-user-button {
	background-color: var(--color-blue);
	width: 50%;
	margin: auto;
}

.create-user-button:hover {
	background-color: var(--color-blue-o);
	color: var(--color-light-blue);
}
#goalSelector {
	outline: none;
	background-color: hsl(204, 5%, 79%);
	border-radius: 8px;
	padding: 10px;
	width: 195px;
}
</style>

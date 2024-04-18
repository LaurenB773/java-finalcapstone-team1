<template>
	<div id="register" class="text-center">
		<form v-on:submit.prevent="register">
			<div role="alert" v-if="registrationErrors" style="color: var(--color-red); text-align: center; margin-bottom: 10px;">
				{{ registrationErrorMsg }}
			</div>
			<div class="form-input-group">
				<label for="username">Username</label>
				<input placeholder="Username" type="text" id="username" v-model="user.username" required autofocus />
			</div>
			<div class="form-input-group">
				<label for="email">Email</label>
				<input placeholder="Email" type="text" name="email" id="email" v-model="user.email" />
			</div>
			<div class="form-input-group">
				<label for="password">Password</label>
				<input placeholder="Password" type="password" id="password" v-model="user.password" required />
			</div>
			<div class="form-input-group">
				<label for="confirmPassword">Confirm Password</label>
				<input placeholder="Re-enter Password" type="password" id="confirmPassword" v-model="user.confirmPassword"
					required />
			</div>
			<div class="form-input-group">
				<label for="goalSelector">Gym Goal </label>
				<!--could put gym name here-->

				<select id="goalSelector" v-model="user.goal">
					<option value="" disabled selected hidden>Goal</option>
					<option value="Weight Loss">Weight Loss</option>
					<option value="Strength/Muscle Building">Strength/Muscle Building</option>
					<option value="Aesthetic Body Building">Aesthetic Body Building</option>
					<option value="Health & Enjoyment">Health & Enjoyment</option>
					<option value="Not Sure">Not Sure</option>
				</select>
			</div>
			<div class="form-input-group">
				<label for="first-name">Preferred First Name</label>
				<input placeholder="First name" type="text" id="first-name" v-model="user.firstName" />
			</div>
			<div class="form-input-group">
				<label for="last-name">Last Name</label>
				<input placeholder="Last name" type="text" id="last-name" v-model="user.lastName" />
			</div>

			<div class="form-input-group">
				<label for="photo">Profile Picture</label>
				<button id="photo" @click="uploadFile" class="upload-button" style="cursor: pointer;"
					:style="user.profilePicture ? 'background-color: var(--color-green);' : ''">{{ user.profilePicture ?
						'Successfully Uploaded!' : 'Upload Image...' }}</button>
			</div>

			<div v-if="user.profilePicture" class="form-input-group">
				Preview:
				<img :src="user.profilePicture" alt="profile-picture" />
			</div>

			<button class="create-user-button" type="submit">
				Create Account
			</button>
		</form>
	</div>
</template>

<script>
import authService from '../services/AuthService'
// DO NOT REMOVE THIS ESLINT DISABLE LINE BELOW
// eslint-disable-next-line import/no-unresolved
import { openUploadModal } from "@bytescale/upload-widget-vue";

const options = {
	apiKey: "free", // Get API key: https://www.bytescale.com/get-started
	maxFileCount: 1
};

export default {
	data() {
		return {
			user: {
				username: '',
				password: '',
				confirmPassword: '',
				role: 'user',
				goal: '',
				email: '',
				firstName: '',
				lastName: '',
				profilePicture: '',
			},
			registrationErrors: false,
			registrationErrorMsg: 'There were problems registering this user.',
		}
	},
	methods: {
		register() {
			if (this.user.password != this.user.confirmPassword) {
				this.registrationErrors = true
				this.registrationErrorMsg = 'Password & Confirm Password do not match.'
			} else {
				authService
					.register(this.user)
					.then(response => {
						if (response.status == 201) {
							this.$router.push({
								path: '/login',
								query: { registration: 'success' },
							})
						}
					})
					.catch(error => {
						const response = error.response
						this.registrationErrors = true
						if (response.status === 400) {
							this.registrationErrorMsg = 'Bad Request: Validation Errors'
						}
					})
			}
		},
		clearErrors() {
			this.registrationErrors = false
			this.registrationErrorMsg = 'There were problems registering this user.'
		},
		uploadFile(event) {
			openUploadModal({
				event,
				options,
				onComplete: files => {
					if (files.length === 0) {
						alert('No files selected.')
					} else {
						this.user.profilePicture = files.map(f => f.fileUrl).join('\n')
					}
				},
			})
		},
	},
}
</script>

<style scoped>
#register {
	display: flex;
	flex-direction: column;
	width: fit-content;
	margin: auto;
	margin-top: 20px;
}

.form-input-group {
	margin-bottom: 1rem;
	color: var(--color-light-blue);
	display: flex;
	margin: auto;
	width: 100%;
	justify-content: space-between;
	padding: 12px;
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

.create-user-button {
	background-color: var(--color-blue);
	display: flex;
	margin: auto;
	padding: 10px;
	margin-top: 15px;
	border-radius: 8px;
	transition: background-color 200ms;
}

.create-user-button:hover {
	background-color: var(--color-blue-o);
	cursor: pointer;
}

.upload {
	background-color: var(--color-blue);
}

#goalSelector {
	outline: none;
	background-color: hsl(204, 5%, 79%);
	border-radius: 8px;
	padding: 10px;
	width: 195px;
}

.upload-button {
	outline: none;
	background-color: hsl(204, 5%, 79%);
	border-radius: 8px;
	padding: 10px;
	width: 200px;
}
</style>

<template>
	<main>
		<h2>My Schedule</h2>
		<h3 v-if="schedule.length === 0">Sign up for events to view</h3>
		<div
			v-for="event in schedule"
			:key="event.scheduleId"
			class="card"
			@click="moreDetails(event)"
		>
			<h3>{{ event.eventName }}</h3>

			<div class="event-content">
				<p>Title:</p>
				<p>{{ event.title }}</p>
			</div>

			<div class="event-content">
				<p>Instructor:</p>
				<p>{{ event.instructor }}</p>
			</div>

			<div class="event-content">
				<p>{{ event.description }}</p>
			</div>

			<div class="event-content">
				<p>Class Time:</p>
				<p>{{ formatTimestamp(event.classTime) }}</p>
			</div>

			<div v-if="events.includes(event)">
				<button @click="cancelEvent(event.scheduleId)">Cancel</button>
			</div>
		</div>
	</main>
</template>

<script>
import UserService from '../services/UserService'
import { formatTimestamp } from '../utils/formatter'

export default {
	data() {
		return {
			schedule: [],
			events: [],
		}
	},

	mounted() {
		UserService.getEventsSignedUpFor().then(response => {
			this.schedule = response.data
		})
	},

	methods: {
		formatTimestamp,

		moreDetails(event) {
			if (this.events.includes(event)) {
				this.events = this.events.filter(e => e !== event)
				return
			} else {
				this.events.push(event)
			}
		},

		cancelEvent(id) {
			UserService.leaveEvent(id)
      this.schedule = this.schedule.filter(event => event.scheduleId !== id)
		},
	},
}
</script>

<style scoped>
main {
	font-size: 18px;
}

.event-content {
	display: flex;
	justify-content: space-between;
}

.card {
	border-radius: 5px;
	padding: 20px;

	font-family: 'M PLUS 1 Code', monospace;
	font-weight: 600;
	background-color: var(--color-light-blue);
	color: black;

  margin-top: 10px;
  margin-bottom: 10px;

  cursor: pointer;
}

.card:hover {
  background-color: var(--color-light-blue-o);
}
</style>

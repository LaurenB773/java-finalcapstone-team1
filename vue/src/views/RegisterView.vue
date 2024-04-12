<template>
  <div id="register" class="text-center">
    <form v-on:submit.prevent="register">
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input placeholder="Username" type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="email">Email</label>
        <input placeholder="Email" type="text" name="email" id="email" v-model="user.email">
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
        <label for="goalSelector">Gym Goal </label> <!--could put gym name here-->
        <select id="goalSelector" v-model="user.goal">
          <option value="weight-loss">Weight Loss</option>
          <option value="strength">Strength/Muscle Building</option>
          <option value="aesthetics">Aesthetic Body Building</option>
          <option value="have-fun">To Have Fun</option>
          <option value="not-sure">Not Sure</option>
        </select>
      </div>
      <div class="form-input-group">
        <label for="first-name">Preferred First Name</label>
        <input placeholder="First name" type="text" id="first-name" v-model="user.firstName">
      </div>
      <div class="form-input-group">
        <label for="last-name">Last Name</label>
        <input placeholder="Last name" type="text" id="last-name" v-model="user.lastName">
      </div>
      <button class="create-user-button" type="submit">Create Account</button>
      <p><router-link class="signup-to-login-link" v-bind:to="{ name: 'login' }">Already have an account? Log
          in.</router-link></p>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

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
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
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

.signup-to-login-link {
  color: hsl(202, 49%, 89.8%);
  margin: 10px;
  position: fixed;
  bottom: 0;
  left: 0;
}

.signup-to-login-link:hover {
  text-decoration: underline;
}
</style>

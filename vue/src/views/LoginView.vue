<template>
  <h1 id="title">FITNESS</h1>
  <h1 id="title2">ELEVATOR</h1>
  <div style="display: flex; gap: 10px; width: fit-content; margin: auto;">
    <div style="height: 3px; width: 40px; border: 1px solid white; background-color: white; border-radius: 10px;">
    </div>
    <div style="height: 3px; width: 20px; border: 1px solid white; background-color: white; border-radius: 10px;">
    </div>
  </div>
  <h2 id="slogan">RISE TO GREATNESS</h2>
  <div id="login">
    <form v-on:submit.prevent="login">
      <h1 id="sign-in"></h1>
      <div role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <div class="form-input-group">
        <input placeholder="Username" type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <input placeholder="Password" type="password" id="password" v-model="user.password" required />
      </div>
      <button class="sign-in-button" type="submit">Sign in</button>
      <p>
        <router-link class="register" v-bind:to="{ name: 'register' }">Need an account? Sign up.</router-link>
      </p>
    </form>
  </div>

  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=M+PLUS+1+Code:wght@100..700&display=swap" rel="stylesheet">
</template>

<script>
import authService from "../services/AuthService";

export default {
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
        role: '',
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
#title {
  font-family: "M PLUS 1 Code", monospace;
  font-optical-sizing: auto;
  font-weight: 600;
  font-style: normal;
  display: flex;
  color: hsl(202, 49%, 89.8%);
  font-size: 50px;
  justify-content: center;
  margin-top: 50px;
}

#title2 {
  font-family: "M PLUS 1 Code", monospace;
  font-weight: 600;
  font-style: normal;
  display: flex;
  color: hsl(202, 49%, 89.8%);
  font-size: 50px;
  justify-content: center;
  padding: 0;
}

#slogan {
  font-family: "M PLUS 1 Code", monospace;
  font-optical-sizing: auto;
  font-weight: 600;
  font-style: normal;
  display: flex;
  color: hsl(202, 49%, 89.8%);
  font-size: medium;
  justify-content: center;
  margin-top: 15px;
}

#login {
  display: flex;
  margin: auto;
  width: fit-content;
  padding: 30px;
}

.form-input-group {
  margin-bottom: 1rem;
}

.form-input-group input {
  outline: none;
  background-color: hsl(204, 5%, 79%);
  border-radius: 8px;
  padding: 10px;
}

.form-input-group input::placeholder {
  color: hsl(200, 2%, 61.4%)
}

/* .form-input-group input:hover{
  background-color:  hsl(203, 8%, 39%, 0.5)
} */

label {
  margin-right: 0.5rem;
}

#sign-in {
  color: hsl(202, 49%, 89.8%);
  font-size: larger;
  padding-bottom: 15px;
}

.form-input-group label {
  color: hsl(202, 49%, 89.8%);
}

.register {
  color: hsl(202, 49%, 89.8%);
  margin: 10px;
  position: fixed;
  bottom: 0;
  left: 0;
}

.register:hover {
  text-decoration: underline;
}

.sign-in-button:hover {
  background-color: var(--color-blue-o);
}

.sign-in-button {
  width: 100%;
  background-color: var(--color-blue);
  border-radius: 8px;
  padding: 5px;
  transition: background-color 200ms;
}
</style>
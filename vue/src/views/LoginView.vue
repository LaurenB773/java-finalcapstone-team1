<template>
  <main>
    <div class="title-container font">
      <div>FITNESS</div>
      <div>ELEVATOR</div>
    </div>

    <div id="divider-container">
      <div class="divider" style="width: 40px"></div>
      <div class="divider" style="width: 20px"></div>
    </div>

    <h2 class="font">RISE TO GREATNESS</h2>

    <form v-on:submit.prevent="login">
      <div role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>
        <input
          placeholder="Username"
          type="text"
          id="username"
          v-model="user.username"
          required
          autofocus
        />

        <input
          placeholder="Password"
          type="password"
          id="password"
          v-model="user.password"
          required
          />

      <button type="submit">Sign in</button>
    </form>
  </main>
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
        role: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>

<style scoped>
.font {
  font-family: "M PLUS 1 Code", monospace;
  font-optical-sizing: auto;
  font-size: 20px;
  font-weight: 600;
  font-style: normal;

  color: var(--color-light-blue);
  text-align: center;
}

.title-container {
  width: fit-content;
  margin: auto;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 50px;

  font-size: 50px;
}

#divider-container {
  display: flex;
  gap: 10px;
  width: fit-content;
  margin: auto;
  margin-top: 10px;
}

.divider {
  height: 3px;
  border: 1px solid white;
  background-color: white;
  border-radius: 10px;
}

form {
  display: flex;
  flex-direction: column;
  width: fit-content;
  margin: auto;
  margin-top: 30px;
}

input {
  outline: none;
  background-color: var(--color-light-blue);
  border-radius: 8px;
  padding: 10px;
  margin-bottom: 1rem;
}

input::placeholder {
  color: var(--color-grey);
}

button {
  width: 100%;
  background-color: var(--color-blue);
  border-radius: 8px;
  padding: 5px;
  font-weight: 600;
  transition: background-color 200ms;
}

button:hover {
  background-color: var(--color-blue-o);
  cursor: pointer;
}

</style>

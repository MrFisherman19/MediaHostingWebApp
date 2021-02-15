<template>
    <v-card>
        <v-card-title>Login</v-card-title>
        <v-divider></v-divider>
        <v-col></v-col>
        <v-card-text>
            <v-row align="center" justify="center" dense>
                <v-col cols="10" class="text-center">
                    <!-- @keyup.enter.native="signIn" -->
                    <v-form>
                        <v-text-field dense label="Username" v-model="username" outlined  color="yellow darken-3"></v-text-field>
                        <v-text-field dense label="Password" type="password" autocomplete="on" outlined v-model="password" color="yellow darken-3"></v-text-field>
                        <v-btn class="mb-4 ma-0 text-capitalize" color="yellow darken-3" @click="login" dark>Log in!</v-btn>
                    </v-form>
                </v-col>
            </v-row>
        </v-card-text>
        <v-divider></v-divider>
        <v-card-actions>
            <v-row align="center" justify="center" dense>
                <v-col cols="10" class="text-center">
                    <div>Don't have an account yet? <router-link to="/register"><v-btn x-small class="register.button" color="yellow darken-3" dark>Register now</v-btn></router-link></div>
                </v-col>
            </v-row>
        </v-card-actions>
    </v-card>
</template>
<script>
import api from '../Api.js';
  export default {
    name: 'LoginForm',
    data:() => ({
        username:'',
        password:'',
    }),
    methods: {
        login() {
            var user = window.btoa(this.username + ":" + this.password);
            api.login(user)
            .then(response => {
                localStorage.setItem('user', user);
                this.$router.push("/files");
                return response;
            }, error => {
                this.error = error.data;
            });
        }        
    }
  }
</script>
<style scoped>
    a {
        text-decoration: none;
    }
</style>

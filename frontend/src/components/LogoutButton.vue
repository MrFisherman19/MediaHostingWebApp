<template>
    <v-btn  dark depressed color="yellow darken-3" @click="logout()" class="text-capitalize"><v-icon>mdi-logout</v-icon>Log out</v-btn>
</template>
<script>
import api from '../Api.js'
export default {
    name: 'LogoutButton',
    methods:{
        logout() {
            document.cookie = "JSESSIONID" +'=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;';
            localStorage.removeItem('user');
            api.destroySessionId();
        }
    }
}
</script>
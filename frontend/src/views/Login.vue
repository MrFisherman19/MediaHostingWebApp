<template>
    <v-container fluid >
        <v-row justify="center" align="center">
            <v-col cols="12" class="text-center">
                <div><br><h1>What do you want to host today?</h1><br></div>
            </v-col>
        </v-row>
        <v-row align="center" justify="center">
            <v-col cols="12" md="3">
                <LoginForm></LoginForm> 
            </v-col>
        </v-row>
    </v-container>  
</template>

<script>
import LoginForm from '../components/LoginForm.vue'
export default {
    components: {
        LoginForm
    }
}   
</script>
<style scoped>
    h1 {
        color: #555
    }
</style>
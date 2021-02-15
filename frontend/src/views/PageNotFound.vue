<template>
    <v-container fluid>
        <v-row justify="center" align="center">
            <v-col cols="12" md="12" class="text-center">
                <h1>404</h1>
                <h2>Nothing is here, even honey :(</h2>
            </v-col>
            <v-col cols="12" md="12" class="text-center">
                <v-btn @click="redirect" color="yellow darken-3" dark class="text-capitalize">Log in!</v-btn>
            </v-col>
        </v-row>
    </v-container>
</template>
<script>
export default {
    name: 'PageNotFound',
    methods: {
        redirect() {
            this.$router.push('/login');
        }
    }
}
</script>
<style scoped>
h1 {
    font-size: 10em;    
}
</style>
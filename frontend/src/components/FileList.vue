<template>
    <v-container>
        <v-banner>You have {{ this.files.length }} files</v-banner>
        <v-card v-for="file in files" :key="file.fileId" class="pa-3 ma-2">
            <v-row dense no-gutters align="center">
                <v-col class="text-end" cols="6" md="2">
                    {{ file.fileName }}
                </v-col>
                <v-col class="text-center" cols="6" md="2">
                    <v-col><v-btn fab color="blue darken-3" dark depressed x-small @click="download(file.fileId)"><v-icon small>mdi-download</v-icon></v-btn></v-col>
                </v-col>
                <v-col class="text-center" cols="12" md="2">
                    {{ file.contentType }}
                </v-col>
                <v-col class="text-center" cols="12" md="2">
                    {{ file.size / 1000 }} KB
                </v-col>
                <v-col class="text-center" cols="12" md="2">
                    {{ file.created }}
                </v-col>
                <v-col class="text-center" cols="12" md="2">
                    <v-col><v-btn fab color="red darken-3" dark depressed x-small><v-icon small>mdi-delete</v-icon></v-btn></v-col>
                </v-col>
            </v-row>
        </v-card>
    </v-container>
</template>
<script>
export default {
    name: 'FileList',
    props:['files'],
    data:()=>({
        
    }),
    methods: {
        download(fileId) {
            this.$emit('download', fileId);
        },
    },
    updated: function () {
        this.$nextTick(function () {
            this.$emit('stopLoading');
        })
    }
}
</script>
<style scoped>

</style>
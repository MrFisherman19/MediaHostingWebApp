<template>
    <v-container fluid>
        <v-row justify="center">
            <v-col md="10">
                <Upload v-on:uploadedFiles='addUploadedToList'></Upload>
            </v-col>
        </v-row>
        <v-row justify="center">
            <v-col md="10">
                <v-progress-linear :active="loading" color="yellow darken-3" indeterminate :striped='true' height="15"></v-progress-linear>
                <FileList v-on:stopLoading="loading==false" :files="this.userFiles" v-on:download="downloadFile"></FileList>
            </v-col>
        </v-row>
    </v-container>
</template>
<script>
import api from '../Api.js'
import axios from 'axios';
import FileList from '../components/FileList.vue'
import Upload from '../components/Upload.vue' 
export default {
    components: {
        FileList,
        Upload
    },
    data:() => ({
        userFiles:[],
        loading: true
    }),
    methods: {
        loadUserFiles() {
            api.getUserFiles()
            .then(data => {
                this.userFiles = data;
                console.log(this.userFiles);
            }).catch(err => {
                console.log(err);
            });
            this.loading = false;
        },
        downloadFile(fileId) {
            axios({
                url: 'http://localhost:8081/download?fileId=' + fileId,
                method: 'GET',
                responseType: 'blob',
                headers: {
                    Authorization: 'Basic ' + localStorage.getItem('user')
                }}).then((response) => {
                var blob =  new Blob([response.data], { 'type' : response.headers['content-type'] });
                const url = window.URL.createObjectURL(blob);
                const link = document.createElement('a');
                const regExpFilename = /filename="(?<filename>.*)"/;
                link.href = url;
                console.log(response.headers['content-disposition']);
                link.setAttribute('download', regExpFilename.exec(response.headers['content-disposition'])?.groups?.filename ?? null); //or any other extension
                document.body.appendChild(link);
                link.click();
            });
        },
        addUploadedToList(uploadedFiles) {

            this.userFiles.push.apply(this.userFiles, uploadedFiles);
        }
    },
    mounted:function() {
        this.loadUserFiles();
    },
}
</script>
<template>
    <v-container>
        <v-card>
            <v-row justify="center" align="center" no-gutters dense>
                <v-col class="text-center" cols="12" md="2">
                    <v-container>
                        <v-btn color="blue darken-3" dark class="text-capitalize" @click="upload"><v-icon>mdi-upload</v-icon>Upload</v-btn>
                    </v-container>
                </v-col>
                <v-col class="text-center" cols="12" md="10">
                    <v-file-input validate-on-blur
                        v-model="userFiles"
                        truncate-length="15"
                        multiple
                        counter
                        small-chips
                        prepend-icon="mdi-file-multiple"
                        color="yellow darken-3"
                        label="Files to upload:"
                        outlined
                        dense
                        class="ma-6 mb-0">
                        <!-- dodac tutaj filter dla audio -->
                        <template v-slot:selection="{ index, text }">
                        <v-chip
                            class="mt-2 mr-0"
                            small
                            color="yellow darken-3"
                            dark
                            close
                            @click:close="removeFileFromList(index)"
                            v-if="index < 6">
                            {{ text }}
                        </v-chip>
                        <span class="ma-2 orange--text"
                            v-else-if="index === 6">
                            + {{ userFiles.length - 6}} File(s)
                        </span>
                    </template>
                    </v-file-input>
                </v-col>
            </v-row>
        </v-card>   
    </v-container>
</template>
<script>
import api from '../Api.js'
export default {
    data:() => ({
        userFiles:[],
    }),
    methods: {
        removeFileFromList(index) {
            this.userFiles.splice(index, 1);
        },
        upload() {
            if (this.userFiles.length !== 0) {
                let formData = new FormData();
                for (const file of this.userFiles) {
                    formData.append("files", file, file.name);
                }
                api.uploadFiles(formData).then(response => {
                    this.userFiles.splice(0, this.userFiles.length);
                    this.$emit('uploadedFiles', response.data);
                }, error => {
                    console.log(error);
                });
            }
        }
    }
}
</script>
<style scoped>
.v-progress-linear__bar, .v-progress-linear__bar__determinate {
  transition: none;
}
</style>
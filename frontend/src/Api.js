import axios from 'axios';
import router from '@/router'

const SERVER_URL = 'http://localhost:8081'

let instance = axios.create({
    baseURL: SERVER_URL,
    timeout: 10000,
});

let authInstance = axios.create({
    baseURL: SERVER_URL,
    timeout: 10000,
});

instance.interceptors.request.use(
    request=>{
        request.headers['Authorization'] = 'Basic ' + localStorage.getItem('user');
        return request;
    });

instance.interceptors.response.use(function (response) {
    return response
  }, function (error) {
    if (error.response.status === 401) {
      router.push({name:'Login'});
    }
    return Promise.reject(error);
  });

export default{
    login:(userBase64)=>authInstance.get('/login', {headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Basic ' + userBase64,
    }}).then(response=>{
        return response;
    }),
    getUserFiles:()=>instance.get('/files').then(response=>{
        return response.data;
    }),
    downloadFile:(fileId)=>instance.get('/download?fileId=' + fileId).then(response=>{
        return response;
    }),
    destroySessionId:()=>instance.get("/login")
        .then(response => {
            console.log(response);
            if (response.status === 401) {
                router.push({name:'AfterLogout'})
            }
    }),
    uploadFiles:(fileObj)=>instance.post('/upload', fileObj, {headers: {
        'Content-Type': 'multipart/form-data'
    }}).then(response => {
        return response;
    })
}
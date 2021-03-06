import Vue from 'vue';
import Vuetify from 'vuetify/lib';
import '@mdi/font/css/materialdesignicons.css'

Vue.use(Vuetify);

export default new Vuetify({
    icons: {
        iconfont: 'mdi'
    },
    theme: {
        dark: false,
        // options: {
        //     themeCache: {
        //         get: key => localStorage.getItem(key),
        //         set: (key, value) => localStorage.setItem(key, value),
        //     }
        // }    
    }
});
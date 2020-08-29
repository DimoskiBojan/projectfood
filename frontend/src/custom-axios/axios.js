import axios from 'axios';

const instance = axios.create({
    baseURL: 'http://localhost:8080',
    headers: {
        'Access-Control-Allow-Origin': '*'
    },
});

instance.interceptors.request.use(function (config) {
    const token = localStorage.getItem("x-access-token");
    config.headers.Authorization = token;

    return config;
});

export default instance;
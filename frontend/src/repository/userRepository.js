import axios from '../custom-axios/axios'

export function login (data) {
    return axios.post("/login", { username: data.username, password: data.password })
        .then(response => {
            localStorage.setItem('x-access-token', response.data.token);
            localStorage.setItem('x-access-token-expiration', Date.now() + 2 * 60 * 60 * 1000);
            return response.data
        })
        .catch(err => Promise.reject('Authentication Failed!'));
}

export function isAuthenticated(){
    return localStorage.getItem('x-access-token') && localStorage.getItem('x-access-token-expiration') > Date.now()
}

export function logOut(){
    localStorage.removeItem('x-access-token');
    localStorage.removeItem('x-access-token-expiration');
}

export function signUp(data){
    return axios.post("/users/sign-up", { username: data.username, password: data.password })
        .then(response => {
            return response.data
        })
        .catch(err => Promise.reject('Sign up failed!'));
}
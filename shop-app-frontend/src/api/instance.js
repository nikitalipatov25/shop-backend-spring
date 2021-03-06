import axios from 'axios'

const instance = axios.create({
    baseURL: 'http://localhost:8080/',
    headers: {
        accept: 'application/json',
        'Access-Control-Allow-Methods': 'GET,HEAD,OPTIONS,POST,PUT',
        'Access-Control-Allow-Headers': 'Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers',
        'Access-Control-Allow-Origin': '*',
    }
})

export default instance

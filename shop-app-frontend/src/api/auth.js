export default function (instance) {
    return {
        login(payload) {
            return instance.post('api/auth/login',  payload)
        }
    }
}
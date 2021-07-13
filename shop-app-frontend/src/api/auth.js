export default function (instance) {
    return {
        login(login, password) {
            return instance.post('/auth/login', login, password)
        }
    }
}
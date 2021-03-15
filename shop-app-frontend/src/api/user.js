export default function (instance) {
    return {
        getSingleUser(UUID) {
            return instance.get('user/' + UUID)
        }
    }
}
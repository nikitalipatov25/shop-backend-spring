export default function (instance) {
    return {
        getCatalog() {
            return instance.get('http://localhost:8080/catalog')
        }
    }
}
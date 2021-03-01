export default function (instance) {
    return {
        getCatalog() {
            return instance.get('catalog')
        },
        getCatalogItemByUUID(UUID) {
            return instance.get('catalog/' + UUID)
        }
    }
}
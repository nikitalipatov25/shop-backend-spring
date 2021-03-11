export default function (instance) {
    return {
        getCatalog(payload) {
            return instance.get(payload)
        },
        getCatalogItemByUUID(UUID) {
            return instance.get('catalog/' + UUID)
        }
    }
}
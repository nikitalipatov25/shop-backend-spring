export default function (instance) {
    return {
        getCatalog() {
            return instance.get('catalog')
        },
        getCatalogWithFilters(searchText) {
            return instance.get('catalog?filter=' + searchText)
        },
        getCatalogItemByUUID(UUID) {
            return instance.get('catalog/' + UUID)
        }
    }
}
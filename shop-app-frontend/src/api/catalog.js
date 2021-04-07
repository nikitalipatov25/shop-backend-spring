export default function (instance) {
    return {
        getCatalog(page = 0, size = 4, sort = 'productName') {
            return instance.get('catalog?page=' + page + '&size=' + size + '&sort=' + sort + ',asc')
        },
        getCatalogWithFilters(sort,  search = 'empty', category= 'empty' ) {
            if (category !== 'empty') {
                return instance.get('catalog?category=' + category)
            }
            if (search !== 'empty') {
                return instance.get('catalog?search=' + search)
            } else {
                return instance.get('catalog?sort=' + sort + ',desc')
            }

        },
        getCatalogItemByUUID(UUID) {
            return instance.get('catalog/' + UUID)
        }
    }
}
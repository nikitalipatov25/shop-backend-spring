export default function (instance) {

    let pagination = ''

    return {
        getCatalog(page = 0, size = 4,  category = 'empty', checkboxesArray = '', search = '', sort = 'productName,asc') {
            pagination = 'page=' + page + '&size=' + size;
            if (checkboxesArray !== '' && search !== '') {
                return instance.get('catalog?' + pagination + '&category=' + category + '&checkboxes=' + checkboxesArray + '&search=' + search + '&sort=' + sort)
            } else if (checkboxesArray === '' && search !== '') {
                return instance.get('catalog?' + pagination + '&category=' + category + '&search=' + search + '&sort=' + sort)
            } else if (checkboxesArray !== '' && search === '') {
                return instance.get('catalog?' + pagination + '&category=' + category +  '&checkboxes=' + checkboxesArray + '&sort=' + sort)
            } else {
                return instance.get('catalog?' + pagination + '&category=' + category + '&sort=' + sort)
            }
        },
        getCategory(page = 0, size = 4, category, checkboxesArray = '', search = '', sort ='productName,asc') {
            pagination = 'page=' + page + '&size=' + size;
            if (checkboxesArray !== '' && search !== '') {
                return instance.get('catalog?' + pagination + '&category=' + category + '&checkboxes=' + checkboxesArray + '&search=' + search + '&sort=' + sort)
            } else if (checkboxesArray === '' && search !== '') {
                return instance.get('catalog?' + pagination + '&category=' + category + '&search=' + search + '&sort=' + sort)
            } else if (checkboxesArray !== '' && search === '') {
                return instance.get('catalog?' + pagination + '&category=' + category + '&checkboxes=' + checkboxesArray + '&sort=' + sort)
            } else {
                return instance.get('catalog?' + pagination + '&category=' + category + '&sort=' + sort)
            }
        },
        getCatalogItemByUUID(UUID) {
            return instance.get('catalog/' + UUID)
        },
    }
}
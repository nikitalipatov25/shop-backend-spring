export default function (instance) {
    return {
        getCart(userId) {
            return instance.get('cart?user=' + userId)
        },
        getCartWithFilters(searchText) {
            return instance.get('cart?filter=' + searchText)
        },
        getCartItemByUUID(UUID) {
            return instance.get('cart/' + UUID)
        },
        addItemToCart(productId, payload) {
            return instance.post('cart?productId=' + productId, payload)
        },
        deleteItemFromCart(UUID) {
            return instance.delete('cart/' + UUID)
        },
        modifyCartItem(UUID, payload) {
            return instance.put('cart/' + UUID, payload)
        }
    }
}
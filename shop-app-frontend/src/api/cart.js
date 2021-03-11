export default function (instance) {
    return {
        getCart() {
            return instance.get('cart')
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
        }
    }
}
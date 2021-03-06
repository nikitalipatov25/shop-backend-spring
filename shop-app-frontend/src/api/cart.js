export default function (instance) {
    return {
        getCart() {
            return instance.get('cart')
        },
        getCartItemByUUID(UUID) {
            return instance.get('cart/' + UUID)
        },
        addItemToCart(payload) {
            return instance.post('cart', payload)
        },
        deleteItemFromCart(UUID) {
            return instance.delete('cart/' + UUID)
        }
    }
}
export default function (instance) {
    return {
        getCart(userId, token) {
            return instance.get('cart/get?user=' + userId, {
                headers: {
                    Authorization: token
                }
            })
        },
        addItemToCart(productId, token) {
            return instance.get('cart/add?productId=' + productId + "&token=" + token, {
                headers: {
                    Authorization: token
                }
            })
        },
        deleteItemFromCart(UUID) {
            return instance.delete('cart/' + UUID)
        },
        modifyCartItem(UUID, payload) {
            return instance.put('cart/' + UUID, payload)
        }
    }
}
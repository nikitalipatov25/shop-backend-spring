export default function (instance) {
    return {
        getOrders(userId) {
            return instance.get('orders/' + userId)
        },
        addOrder(payload) {
            return instance.post('orders?orderType=' + payload)
        }
    }
}
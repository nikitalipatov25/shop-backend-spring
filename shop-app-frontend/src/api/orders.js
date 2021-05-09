export default function (instance) {
    return {
        getOrders(userId) {
            return instance.get('orders/' + userId)
        },
        addOrder(userId) {
            return instance.post('orders?user=' + userId)
        }
    }
}
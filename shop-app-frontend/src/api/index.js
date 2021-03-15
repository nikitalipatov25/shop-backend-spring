import instance from './instance'

import catalogModule from './catalog'
import cartModule from './cart';
import userModule from './user'

export default {
    catalog: catalogModule(instance),
    cart: cartModule(instance),
    user: userModule(instance)
}
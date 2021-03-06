import instance from './instance'

import catalogModule from './catalog'
import cartModule from './cart';

export default {
    catalog: catalogModule(instance),
    cart: cartModule(instance)
}
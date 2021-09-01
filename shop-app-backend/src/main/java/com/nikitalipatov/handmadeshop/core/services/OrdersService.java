//package com.nikitalipatov.handmadeshop.core.services;
//
//import com.nikitalipatov.handmadeshop.core.models.Cart;
//import com.nikitalipatov.handmadeshop.core.models.Orders;
//import com.nikitalipatov.handmadeshop.core.repositories.OrdersRepository;
//import com.nikitalipatov.handmadeshop.helpers.CartInfo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.servlet.http.HttpServletRequest;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//import java.util.UUID;
//
//@Service
//@Transactional
//public class OrdersService {
//
//    private final OrdersRepository ordersRepository;
//    private final CartService cartService;
//    private final ProductService productService;
//    private final UserService userService;
//
//    @Autowired
//    public OrdersService (OrdersRepository ordersRepository, CartService cartService, ProductService productService, UserService userService) {
//        this.ordersRepository = ordersRepository;
//        this.cartService = cartService;
//        this.productService = productService;
//        this.userService = userService;
//    }
//
//    public Orders generateOrder(Orders orders, HttpServletRequest request) {
//        var user = userService.findUser(request);
//        var userCart = cartService.findCartByUserID(user.get().getUsername());
//        Orders newOrder = new Orders();
//        newOrder.setOrderId(UUID.randomUUID());
//        newOrder.setUserId(user.get().getId());
//        CartInfo cartInfo = calculateCart(userCart);
//        newOrder.setProductsInfo(cartInfo.getProductsInfo());
//        newOrder.setFinalPrice(cartInfo.getTotalCost());
//        newOrder.setOrderType(orders.getOrderType());
//        newOrder.setOrderStatus("Принят в магазине");
//        newOrder.setOrderDate(calculateOrderDate());
//        /*
//        ВРЕМЕННЫЕ КАСТЫЛИ
//        */
//        reorganizeCatalog(userCart);
//        cartService.deleteAllUserCart(user.get().getUsername());
//        return ordersRepository.save(newOrder);
//
//    }
//
//    public Page<Orders> getUserOrders(HttpServletRequest request, Pageable pageable) {
//        var user = userService.findUser(request);
//        return ordersRepository.findByUserId(user.get().getId(), pageable);
//    }
//
//    public String calculateOrderDate() {
//        Date currentDate = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
//        return simpleDateFormat.format(currentDate);
//    }
//
//    public CartInfo calculateCart(List<Cart> cart) {
//        CartInfo cartInfo = new CartInfo();
//        String productsInfo = "";
//        int totalItems = 0;
//        double totalCost = 0.0;
//        double discount = 0.0;
//        for (int i = 0; i < cart.size(); i++) {
//            productsInfo = productsInfo + "Товар: " + cart.get(i).getCatalogProductName() + " Кол-во: " + cart.get(i).getSelectedProductKol() + " ";
//            totalItems = totalItems + cart.get(i).getSelectedProductKol();
//            totalCost = totalCost + cart.get(i).getProductCost();
//        }
//        cartInfo.setProductsInfo(productsInfo);
//        cartInfo.setTotalItems(totalItems);
//        if (totalItems >= 10) {
//            discount = totalCost * 0.05;
//            totalCost = totalCost - discount;
//        }
//        cartInfo.setTotalCost(totalCost);
//        return cartInfo;
//    }
//
//    public void reorganizeCatalog(List<Cart> cart) {
//        for (int i = 0; i < cart.size(); i++) {
//            productService.modifyKolInCatalog(cart.get(i).getProductId(), cart.get(i).getSelectedProductKol());
//        }
//
//    }
//
//}
//
//

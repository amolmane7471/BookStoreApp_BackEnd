package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.OrderDTO;
import com.bridgelabz.bookstoreapp.entity.OrderData;

import java.util.List;

public interface IOrderService {
    OrderData placeOrder(long userId, OrderDTO orderDto);

    String cancelOrder(int orderId, long userId);
    List<OrderData> userOrders(long userId);

    List<OrderData> getAllOrders();

}

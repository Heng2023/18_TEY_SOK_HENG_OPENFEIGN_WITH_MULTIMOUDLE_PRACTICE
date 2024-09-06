package org.example.service;

import org.example.model.request.OrderRequest;
import org.example.model.response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(OrderRequest orderRequest);
    List<OrderResponse> getAllOrders();
    OrderResponse getOrderById(Long orderId);
    void deleteOrderById(Long orderId);
    OrderResponse updateOrderById(Long orderId, OrderRequest orderRequest);
}

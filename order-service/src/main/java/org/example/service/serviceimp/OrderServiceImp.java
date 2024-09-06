package org.example.service.serviceimp;

import lombok.AllArgsConstructor;
import org.example.feignclient.CustomerClient;
import org.example.feignclient.ProductClient;
import org.example.model.Order;
import org.example.model.request.OrderRequest;
import org.example.model.response.CustomerResponse;
import org.example.model.response.OrderResponse;
import org.example.model.response.ProductResponse;
import org.example.repository.OrderRepository;
import org.example.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerFeignClient;
    private final ProductClient productFeignClient;

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        CustomerResponse customer = customerFeignClient.getCustomer(orderRequest.getCustomerId()).getBody().getPayload();
        List<ProductResponse> products = orderRequest.getProductIds().stream()
                .map(productId -> productFeignClient.getProduct(productId).getBody().getPayload())
                .collect(Collectors.toList());

        Order order = new Order();
        order.setCustomerId(orderRequest.getCustomerId());
        order.setProductIds(orderRequest.getProductIds());
        order.setOrderDate(orderRequest.getOrderDate());
        Order savedOrder = orderRepository.save(order);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderId(savedOrder.getOrderId());
        orderResponse.setCustomerDetails(customer);
        orderResponse.setProductDetails(products);
        orderResponse.setOrderDate(savedOrder.getOrderDate());

        return orderResponse;
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(order -> {
                    CustomerResponse customer = customerFeignClient.getCustomer(order.getCustomerId()).getBody().getPayload();
                    List<ProductResponse> products = order.getProductIds().stream()
                            .map(productId -> productFeignClient.getProduct(productId).getBody().getPayload())
                            .collect(Collectors.toList());

                    OrderResponse orderResponse = new OrderResponse();
                    orderResponse.setOrderId(order.getOrderId());
                    orderResponse.setCustomerDetails(customer);
                    orderResponse.setProductDetails(products);
                    orderResponse.setOrderDate(order.getOrderDate());
                    return orderResponse;
                }).collect(Collectors.toList());
    }

    @Override
    public OrderResponse getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        CustomerResponse customer = customerFeignClient.getCustomer(order.getCustomerId()).getBody().getPayload();
        List<ProductResponse> products = order.getProductIds().stream()
                .map(productId -> productFeignClient.getProduct(productId).getBody().getPayload())
                .collect(Collectors.toList());

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderId(order.getOrderId());
        orderResponse.setCustomerDetails(customer);
        orderResponse.setProductDetails(products);
        orderResponse.setOrderDate(order.getOrderDate());

        return orderResponse;
    }

    @Override
    public void deleteOrderById(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public OrderResponse updateOrderById(Long orderId, OrderRequest orderRequest) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        CustomerResponse customer = customerFeignClient.getCustomer(orderRequest.getCustomerId()).getBody().getPayload();
        List<ProductResponse> products = orderRequest.getProductIds().stream()
                .map(productId -> productFeignClient.getProduct(productId).getBody().getPayload())
                .collect(Collectors.toList());

        order.setCustomerId(orderRequest.getCustomerId());
        order.setProductIds(orderRequest.getProductIds());
        order.setOrderDate(orderRequest.getOrderDate());
        Order updatedOrder = orderRepository.save(order);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderId(updatedOrder.getOrderId());
        orderResponse.setCustomerDetails(customer);
        orderResponse.setProductDetails(products);
        orderResponse.setOrderDate(updatedOrder.getOrderDate());

        return orderResponse;
    }
}

package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.request.OrderRequest;
import org.example.model.response.ApiResponse;
import org.example.model.response.OrderResponse;
import org.example.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> createOrder(@RequestBody OrderRequest orderRequest) {
        OrderResponse orderResponse = orderService.createOrder(orderRequest);
        ApiResponse<OrderResponse> apiResponse = new ApiResponse<>(
                "An order has been created successfully.",
                orderResponse,
                HttpStatus.CREATED,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getAllOrders() {
        List<OrderResponse> orders = orderService.getAllOrders();
        ApiResponse<List<OrderResponse>> apiResponse = new ApiResponse<>(
                "Orders have been retrieved successfully.",
                orders,
                HttpStatus.OK,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse<OrderResponse>> getOrderById(@PathVariable Long orderId) {
        OrderResponse orderResponse = orderService.getOrderById(orderId);
        ApiResponse<OrderResponse> apiResponse = new ApiResponse<>(
                "An order has been retrieved successfully.",
                orderResponse,
                HttpStatus.OK,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<ApiResponse<?>> deleteOrderById(@PathVariable Long orderId) {
        orderService.deleteOrderById(orderId);
        ApiResponse<Void> apiResponse = new ApiResponse<>(
                "An order has been deleted successfully.",
                null,
                HttpStatus.OK,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<ApiResponse<OrderResponse>> updateOrderById(
            @PathVariable Long orderId, @RequestBody OrderRequest orderRequest) {
        OrderResponse orderResponse = orderService.updateOrderById(orderId, orderRequest);
        ApiResponse<OrderResponse> apiResponse = new ApiResponse<>(
                "An order has been updated successfully.",
                orderResponse,
                HttpStatus.OK,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}

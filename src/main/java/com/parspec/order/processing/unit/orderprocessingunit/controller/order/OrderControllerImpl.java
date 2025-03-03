package com.parspec.order.processing.unit.orderprocessingunit.controller.order;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.parspec.order.processing.unit.orderprocessingunit.dto.OrderRequest;
import com.parspec.order.processing.unit.orderprocessingunit.model.Order;
import com.parspec.order.processing.unit.orderprocessingunit.service.order.OrderService;

@RestController
public class OrderControllerImpl implements OrderApi {
    private final OrderService orderService;

    public OrderControllerImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public ResponseEntity<String> placeOrder(String userId, String orderId, List<String> itemIds, double totalAmount) {
        Order order = orderService.processOrder(new OrderRequest(userId, orderId, itemIds, totalAmount));
        return ResponseEntity.ok("Order placed successfully with ID: " + order);
    }
}
package com.parspec.order.processing.unit.orderprocessingunit.processor;

import java.util.List;

import com.parspec.order.processing.unit.orderprocessingunit.model.Order;

public interface OrderProcessor {
    void process(String userId, String orderId, List<String> itemIds, double totalAmount);
    String findOrderStatus(String orderId);
    Order save(Order order);
}

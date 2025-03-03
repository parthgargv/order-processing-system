package com.parspec.order.processing.unit.orderprocessingunit.service.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parspec.order.processing.unit.orderprocessingunit.dto.OrderRequest;
import com.parspec.order.processing.unit.orderprocessingunit.model.Order;
import com.parspec.order.processing.unit.orderprocessingunit.model.OrderStatus;
import com.parspec.order.processing.unit.orderprocessingunit.processor.OrderProcessor;
import com.parspec.order.processing.unit.orderprocessingunit.service.Services;

@Service
public class OrderService extends Services{
    private final OrderProcessor orderProcessingHandler;

    public OrderService(OrderProcessor orderProcessingHandler) {
        this.orderProcessingHandler = orderProcessingHandler;
    }

    @Transactional
    public Order processOrder(OrderRequest request) {
        Order order = new Order(request.orderId(), request.userId(), null, 0, OrderStatus.PENDING);
        return orderProcessingHandler.save(order);
    }
}

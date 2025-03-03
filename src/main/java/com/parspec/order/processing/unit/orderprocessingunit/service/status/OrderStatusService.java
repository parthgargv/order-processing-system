package com.parspec.order.processing.unit.orderprocessingunit.service.status;

import com.parspec.order.processing.unit.orderprocessingunit.processor.OrderProcessor;
import com.parspec.order.processing.unit.orderprocessingunit.service.Services;

public class OrderStatusService extends Services{
    private final OrderProcessor orderProcessingHandler;

    public OrderStatusService(OrderProcessor orderProcessingHandler){
        this.orderProcessingHandler = orderProcessingHandler;
    }

    public String findOrderStatus(String orderId) {
        return orderProcessingHandler.findOrderStatus(orderId);
    }

}

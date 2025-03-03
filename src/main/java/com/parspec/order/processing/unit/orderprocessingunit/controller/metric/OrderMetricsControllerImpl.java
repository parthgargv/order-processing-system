package com.parspec.order.processing.unit.orderprocessingunit.controller.metric;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.parspec.order.processing.unit.orderprocessingunit.model.OrderStatus;
import com.parspec.order.processing.unit.orderprocessingunit.service.ServiceFactory;
import com.parspec.order.processing.unit.orderprocessingunit.service.ServiceType;
import com.parspec.order.processing.unit.orderprocessingunit.service.Services;
import com.parspec.order.processing.unit.orderprocessingunit.service.metric.OrderMetricsService;

@RestController
public class OrderMetricsControllerImpl implements OrderMetricsApi {
    private final ServiceFactory serviceFactory;
    private final Services orderMetricsService;

    public OrderMetricsControllerImpl(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
        this.orderMetricsService = this.serviceFactory.getServices(ServiceType.ORDER_METRIC_SERVICE);
    }

    @Override
    public ResponseEntity<Long> getTotalOrders() {
        return ResponseEntity.ok(((OrderMetricsService) orderMetricsService).getTotalOrders());
    }

    @Override
    public ResponseEntity<Long> getAverageProcessingTime() {
        return ResponseEntity.ok(((OrderMetricsService) orderMetricsService).getAverageProcessingTime());
    }

    @SuppressWarnings("unchecked")
    @Override
    public ResponseEntity<Map<OrderStatus, Long>> getOrderStatusCounts() {
        return (ResponseEntity<Map<OrderStatus, Long>>) ((OrderMetricsService) orderMetricsService).getOrderStatusCounts();
    }
}
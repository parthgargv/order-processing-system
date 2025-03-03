package com.parspec.order.processing.unit.orderprocessingunit.controller.metric;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parspec.order.processing.unit.orderprocessingunit.model.OrderStatus;

@RestController
@RequestMapping("/metrics")
public interface OrderMetricsApi {
    @GetMapping("/total")
    ResponseEntity<Long> getTotalOrders();

    @GetMapping("/average-time")
    ResponseEntity<Long> getAverageProcessingTime();

    @GetMapping("/status-count")
    ResponseEntity<Map<OrderStatus, Long>> getOrderStatusCounts();
}

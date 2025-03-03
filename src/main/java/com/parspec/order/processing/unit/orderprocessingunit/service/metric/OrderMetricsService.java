package com.parspec.order.processing.unit.orderprocessingunit.service.metric;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.parspec.order.processing.unit.orderprocessingunit.model.Order;
import com.parspec.order.processing.unit.orderprocessingunit.model.OrderStatus;
import com.parspec.order.processing.unit.orderprocessingunit.repository.OrderRepository;
import com.parspec.order.processing.unit.orderprocessingunit.service.Services;

@Service
public class OrderMetricsService extends Services {
    private final OrderRepository orderRepository;

    public OrderMetricsService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public long getTotalOrders() {
        return orderRepository.count();
    }

    public long getAverageProcessingTime() {
        return TimeUnit.MILLISECONDS.toSeconds(2); // Simulated processing time
    }

    public Map<OrderStatus, Long> getOrderStatusCounts() {
        return orderRepository.findAll().stream()
                .collect(Collectors.groupingBy(Order::getStatus, Collectors.counting()));
    }
}

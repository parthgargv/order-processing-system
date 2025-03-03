package com.parspec.order.processing.unit.orderprocessingunit.controller.status;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders/status")
public interface OrderStatusApi {
    @GetMapping
    ResponseEntity<String> getOrderStatus(@RequestParam String orderId);
}
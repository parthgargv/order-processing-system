package com.parspec.order.processing.unit.orderprocessingunit.controller.status;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.parspec.order.processing.unit.orderprocessingunit.service.ServiceFactory;
import com.parspec.order.processing.unit.orderprocessingunit.service.ServiceType;
import com.parspec.order.processing.unit.orderprocessingunit.service.Services;
import com.parspec.order.processing.unit.orderprocessingunit.service.status.OrderStatusService;

@RestController
public class OrderStatusControllerImpl implements OrderStatusApi {
    private final ServiceFactory serviceFactory;
    private final Services orderStatusService;

    public OrderStatusControllerImpl(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
        this.orderStatusService = this.serviceFactory.getServices(ServiceType.ORDER_STATUS_SERVICE);
    }

    @Override
    public ResponseEntity<String> getOrderStatus(String orderId) {
        String status = ((OrderStatusService) orderStatusService).findOrderStatus(orderId);
        return ResponseEntity.ok(status);
    }
}



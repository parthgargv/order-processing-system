package com.parspec.order.processing.unit.orderprocessingunit.service;

import java.util.EnumMap;
import java.util.Map;

import com.parspec.order.processing.unit.orderprocessingunit.service.metric.OrderMetricsService;
import com.parspec.order.processing.unit.orderprocessingunit.service.order.OrderService;
import com.parspec.order.processing.unit.orderprocessingunit.service.status.OrderStatusService;

public class ServiceFactory {
    private final Map<ServiceType, Services> serviceMap = new EnumMap<>(ServiceType.class);

    public ServiceFactory(OrderMetricsService orderMetricsService,
                          OrderStatusService orderStatusService,
                          OrderService orderService){
        
        serviceMap.put(ServiceType.ORDER_METRIC_SERVICE, orderMetricsService);
        serviceMap.put(ServiceType.ORDER_STATUS_SERVICE, orderStatusService);
        serviceMap.put(ServiceType.ORDER_SERVICE, orderService);
    }

    public Services getServices(ServiceType serviceType){
        return serviceMap.get(serviceType);
    }



}

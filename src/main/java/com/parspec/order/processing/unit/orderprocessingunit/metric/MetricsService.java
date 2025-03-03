// package com.parspec.order.processing.unit.orderprocessingunit.metric;

// import java.util.Map;
// import java.util.concurrent.ConcurrentHashMap;

// import org.springframework.stereotype.Service;

// @Service
// public class MetricsService {
//     private final Map<String, Integer> metrics = new ConcurrentHashMap<>();

//     public void incrementMetric(String metric) {
//         metrics.merge(metric, 1, Integer::sum);
//     }

//     public Map<String, Integer> getOrderMetrics() {
//         return metrics;
//     }
// }
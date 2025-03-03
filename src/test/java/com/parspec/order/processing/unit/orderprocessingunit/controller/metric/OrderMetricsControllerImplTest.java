// package com.parspec.order.processing.unit.orderprocessingunit.controller.metric;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.Mockito.when;

// import java.util.Map;

// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.springframework.http.ResponseEntity;

// import com.parspec.order.processing.unit.orderprocessingunit.model.OrderStatus;
// import com.parspec.order.processing.unit.orderprocessingunit.service.metric.OrderMetricsService;

// public class OrderMetricsControllerImplTest {

//     @Mock
//     private OrderMetricsService orderMetricsService;

//     @InjectMocks
//     private OrderMetricsControllerImpl orderMetricsController;

//     public OrderMetricsControllerImplTest() {
//         MockitoAnnotations.openMocks(this);
//     }

//     @Test
//     void testGetTotalOrders() {
//         when(orderMetricsService.getTotalOrders()).thenReturn(100L);

//         ResponseEntity<Long> response = orderMetricsController.getTotalOrders();
//         assertEquals(100L, response.getBody());
//     }

//     @Test
//     void testGetAverageProcessingTime() {
//         when(orderMetricsService.getAverageProcessingTime()).thenReturn(200L);

//         ResponseEntity<Long> response = orderMetricsController.getAverageProcessingTime();
//         assertEquals(200L, response.getBody());
//     }

//     @Test
//     void testGetOrderStatusCounts() {
//         Map<OrderStatus, Long> mockStatusCounts = Map.of(
//             OrderStatus.PENDING, 10L,
//             OrderStatus.PROCESSING, 5L,
//             OrderStatus.COMPLETED, 20L
//         );
//         when(orderMetricsService.getOrderStatusCounts()).thenReturn(mockStatusCounts);

//         ResponseEntity<Map<OrderStatus, Long>> response = orderMetricsController.getOrderStatusCounts();
//         assertEquals(mockStatusCounts, response.getBody());
//     }
// }

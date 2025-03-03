// package com.parspec.order.processing.unit.orderprocessingunit.controller.order;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.when;

// import java.util.List;
// import java.util.Map;

// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.ResponseEntity;

// import com.parspec.order.processing.unit.orderprocessingunit.controller.metric.OrderMetricsControllerImpl;
// import com.parspec.order.processing.unit.orderprocessingunit.dto.OrderRequest;
// import com.parspec.order.processing.unit.orderprocessingunit.model.Order;
// import com.parspec.order.processing.unit.orderprocessingunit.model.OrderStatus;
// import com.parspec.order.processing.unit.orderprocessingunit.service.metric.OrderMetricsService;
// import com.parspec.order.processing.unit.orderprocessingunit.service.order.OrderService;

// @SpringBootTest
// @AutoConfigureMockMvc
// public class OrderControllerImplTest {

//         @Mock
//     private OrderMetricsService orderMetricsService;

//     @InjectMocks
//     private OrderMetricsControllerImpl orderMetricsController;

//     @Mock
//     private OrderService orderService;

//     @InjectMocks
//     private OrderControllerImpl orderController;

//     public OrderControllerImplTest() {
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

//     @Test
//     void testPlaceOrder() {
//         Order mockOrder = new Order("order123", null, null, 0, null);
//         when(orderService.processOrder(any(OrderRequest.class))).thenReturn(mockOrder);
//         ResponseEntity<String> response = orderController.placeOrder("user123", "order123", List.of("item1", "item2"), 50.0);
//         assertEquals("Order placed successfully with ID: " + mockOrder, response.getBody());
//     }
// }

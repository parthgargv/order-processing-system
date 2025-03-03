// package com.parspec.order.processing.unit.orderprocessingunit.controller.status;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.Mockito.when;

// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.springframework.http.ResponseEntity;

// import com.parspec.order.processing.unit.orderprocessingunit.service.status.OrderStatusService;

// public class OrderStatusControllerImplTest {

//     @Mock
//     private OrderStatusService orderStatusService;

//     @InjectMocks
//     private OrderStatusControllerImpl orderStatusController;

//     public OrderStatusControllerImplTest() {
//         MockitoAnnotations.openMocks(this);
//     }

//     @Test
//     void testGetOrderStatus() {
//         when(orderStatusService.findOrderStatus("order123")).thenReturn("COMPLETED");
//         ResponseEntity<String> response = orderStatusController.getOrderStatus("order123");
//         assertEquals("COMPLETED", response.getBody());
//     }
// }

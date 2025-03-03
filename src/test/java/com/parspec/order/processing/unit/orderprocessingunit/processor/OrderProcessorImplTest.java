// package com.parspec.order.processing.unit.orderprocessingunit.processor;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.atLeast;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import java.util.List;
// import java.util.Optional;
// import java.util.concurrent.TimeUnit;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.ArgumentCaptor;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;

// import com.parspec.order.processing.unit.orderprocessingunit.model.Order;
// import com.parspec.order.processing.unit.orderprocessingunit.model.OrderStatus;
// import com.parspec.order.processing.unit.orderprocessingunit.repository.OrderRepository;

// class OrderProcessorImplTest {

//     @Mock
//     private OrderRepository orderRepository;

//     @InjectMocks
//     private OrderProcessorImpl orderProcessor;

//     @BeforeEach
//     void setUp() {
//         MockitoAnnotations.openMocks(this);
//     }

//     @Test
//     void testSaveOrder() {
//         Order order = new Order("order123", "user1", List.of("item1", "item2"), 100.0, OrderStatus.PENDING);
//         when(orderRepository.save(order)).thenReturn(order);

//         Order savedOrder = orderProcessor.save(order);

//         assertNotNull(savedOrder);
//         assertEquals("order123", savedOrder.getOrderId());
//         verify(orderRepository, times(1)).save(order);
//     }

//     @Test
//     void testFindOrderStatus_OrderExists() {
//         Order order = new Order("order123", "user1", List.of("item1"), 50.0, OrderStatus.COMPLETED);
//         when(orderRepository.findById("order123")).thenReturn(Optional.of(order));

//         String status = orderProcessor.findOrderStatus("order123");

//         assertEquals("COMPLETED", status);
//         verify(orderRepository, times(1)).findById("order123");
//     }

//     @Test
//     void testFindOrderStatus_OrderNotFound() {
//         when(orderRepository.findById("order123")).thenReturn(Optional.empty());

//         String status = orderProcessor.findOrderStatus("order123");

//         assertEquals("Order Not Found", status);
//         verify(orderRepository, times(1)).findById("order123");
//     }

//     @Test
//     void testProcessOrder() throws InterruptedException {
//         Order order = new Order("order123", "user1", List.of("item1", "item2"), 100.0, OrderStatus.PENDING);
//         when(orderRepository.save(any())).thenReturn(order);
//         when(orderRepository.findById("order123")).thenReturn(Optional.of(order));

//         orderProcessor.process("user1", "order123", List.of("item1", "item2"), 100.0);

//         // Wait for async processing to complete
//         TimeUnit.SECONDS.sleep(3);

//         ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);
//         verify(orderRepository, atLeast(2)).save(orderCaptor.capture());

//         List<Order> savedOrders = orderCaptor.getAllValues();
//         assertEquals(OrderStatus.PENDING, savedOrders.get(0).getStatus());
//         assertEquals(OrderStatus.PROCESSING, savedOrders.get(1).getStatus());
//         assertEquals(OrderStatus.COMPLETED, savedOrders.get(2).getStatus());
//     }
// }
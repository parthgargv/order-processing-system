package com.parspec.order.processing.unit.orderprocessingunit.processor;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.parspec.order.processing.unit.orderprocessingunit.model.Order;
import com.parspec.order.processing.unit.orderprocessingunit.model.OrderStatus;
import com.parspec.order.processing.unit.orderprocessingunit.repository.OrderRepository;

@Component
public class OrderProcessorImpl implements OrderProcessor {

    private static final Logger logger = LoggerFactory.getLogger(OrderProcessorImpl.class);
    private final BlockingQueue<ProcessingTask> orderQueue = new LinkedBlockingQueue<>();
    private final ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
    private final OrderRepository orderRepository;
    private static final int MAX_RETRIES = 3;

    public OrderProcessorImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public String findOrderStatus(String orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        return order.map(o -> o.getStatus().name()).orElse("Order Not Found");
    }

    @Override
    public void process(String userId, String orderId, List<String> itemIds, double totalAmount) {
        // Set initial status to PENDING
        Order order = new Order(orderId, userId, itemIds, totalAmount, OrderStatus.PENDING);
        orderRepository.save(order);
        orderQueue.offer(new ProcessingTask(userId, orderId, itemIds, totalAmount, 0));
        executor.submit(this::processQueue);
    }

    @Async
    private void processQueue() {
        while (!orderQueue.isEmpty()) {
            try {
                ProcessingTask task = orderQueue.take();
                processOrder(task);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Queue processing interrupted", e);
            }
        }
    }

    private void processOrder(ProcessingTask task) {
        try {
            // Update status to PROCESSING
            Order order = orderRepository.findById(task.orderId).orElseThrow();
            order.setStatus(OrderStatus.PROCESSING);
            orderRepository.save(order);
            logger.info("Order ID: {} is now PROCESSING", task.orderId);

            // Simulate processing time
            Thread.sleep(2000);

            // Update status to COMPLETED
            order.setStatus(OrderStatus.COMPLETED);
            orderRepository.save(order);
            logger.info("Order ID: {} is now COMPLETED", task.orderId);
        } catch (Exception e) {
            if (task.retryCount < MAX_RETRIES) {
                logger.warn("Retrying Order ID: {} (Attempt {}/{})", task.orderId, task.retryCount + 1, MAX_RETRIES);
                orderQueue.offer(new ProcessingTask(task.userId, task.orderId, task.itemIds, task.totalAmount, task.retryCount + 1));
            } else {
                logger.error("Failed to process Order ID: {} after {} attempts", task.orderId, MAX_RETRIES, e);
            }
        }
    }

    private static class ProcessingTask {
        String userId;
        String orderId;
        List<String> itemIds;
        double totalAmount;
        int retryCount;

        ProcessingTask(String userId, String orderId, List<String> itemIds, double totalAmount, int retryCount) {
            this.userId = userId;
            this.orderId = orderId;
            this.itemIds = itemIds;
            this.totalAmount = totalAmount;
            this.retryCount = retryCount;
        }
    }
}
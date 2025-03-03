package com.parspec.order.processing.unit.orderprocessingunit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.parspec.order.processing.unit.orderprocessingunit.model.Order;

public interface OrderRepository extends JpaRepository<Order, String> {}
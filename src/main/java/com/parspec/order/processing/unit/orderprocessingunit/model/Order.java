package com.parspec.order.processing.unit.orderprocessingunit.model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    private String orderId;
    private String userId;
    @ElementCollection
    private List<String> itemIds;
    private double totalAmount;

    @Enumerated(EnumType.STRING) // Store enum as a string in the database
    private OrderStatus status; // Use OrderStatus enum instead of String
}

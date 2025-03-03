package com.parspec.order.processing.unit.orderprocessingunit.controller.order;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@RestController
@RequestMapping("/orders")
public interface OrderApi {
    @PostMapping
    ResponseEntity<String> placeOrder(
        @NotNull @NotEmpty @Size(min = 3, max = 50) @RequestParam String userId,
        @NotNull @NotEmpty @Size(min = 3, max = 50) @RequestParam String orderId,
        @NotNull @Size(min = 1) @RequestParam List<@NotEmpty String> itemIds,
        @Min(1) @Digits(integer = 10, fraction = 2) @RequestParam double totalAmount
    );
}

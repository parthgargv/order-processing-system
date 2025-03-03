package com.parspec.order.processing.unit.orderprocessingunit.dto;

import java.util.List;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record OrderRequest(
    @NotNull @NotEmpty String userId,
    @NotNull @NotEmpty String orderId,
    @NotNull @Size(min = 1) List<@NotEmpty String> itemIds,
    @Min(1) @Digits(integer = 10, fraction = 2) double totalAmount
) {}

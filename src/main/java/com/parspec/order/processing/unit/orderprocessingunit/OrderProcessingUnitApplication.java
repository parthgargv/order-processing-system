package com.parspec.order.processing.unit.orderprocessingunit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class OrderProcessingUnitApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderProcessingUnitApplication.class, args);
	}

}

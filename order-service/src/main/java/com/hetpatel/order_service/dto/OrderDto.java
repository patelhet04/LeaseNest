package com.hetpatel.order_service.dto;

import com.hetpatel.order_service.model.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderDto {
    private Long id;

    private Long productId;
    private Long userId;

    private String leaseDuration;
    private Long totalPrice;

    private OrderStatus status;
}

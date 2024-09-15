package com.hetpatel.product_service.dto;

import com.hetpatel.product_service.model.LeaseStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private Long id;
    @NotNull(message = "Name is required")
    private String name;

    private String description;
    @NotNull(message = "Price is required")
    private BigDecimal price;

    private String category;
    private LeaseStatus status;

    private String imageUrl;

}

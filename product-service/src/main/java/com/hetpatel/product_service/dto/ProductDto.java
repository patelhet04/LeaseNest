package com.hetpatel.product_service.dto;

import com.hetpatel.product_service.model.LeaseStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    private BigDecimal price;

    @Min(value = 0, message = "Available units cannot be negative")
    private int availableUnits;

    @NotBlank(message = "Category is required")
    private String category;

    private LeaseStatus status; // Include if needed

    private String imageUrl;
}

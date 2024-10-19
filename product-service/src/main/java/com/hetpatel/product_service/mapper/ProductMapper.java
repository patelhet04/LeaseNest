package com.hetpatel.product_service.mapper;

import com.hetpatel.product_service.dto.ProductDto;
import com.hetpatel.product_service.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

    // Map ProductDto to Product (for create operations)
    Product toEntity(ProductDto productDto);

    // Map Product to ProductDto (for read operations)
    ProductDto toDto(Product product);

    // Update existing Product entity from ProductDto (for update operations)
    void updateProductFromDto(ProductDto productDto, @MappingTarget Product product);
}

//Get All Orders:
//GET /orders - Retrieve all orders, possibly with pagination.
//Get Orders by User:
//GET /orders/user/{userId} - Retrieve all orders placed by a specific user.
//Update Order Status:
//PUT /orders/{orderId}/status - Update the status of an order (e.g., from PENDING to COMPLETED).
//Cancel Order:
//DELETE /orders/{orderId} or POST /orders/{orderId}/cancel - Cancel an order.
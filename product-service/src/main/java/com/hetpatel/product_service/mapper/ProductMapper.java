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


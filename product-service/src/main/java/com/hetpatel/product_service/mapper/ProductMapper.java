package com.hetpatel.product_service.mapper;

import com.hetpatel.product_service.dto.ProductDto;
import com.hetpatel.product_service.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

    void updateProductFromDto(ProductDto productDto,@MappingTarget Product product);
}

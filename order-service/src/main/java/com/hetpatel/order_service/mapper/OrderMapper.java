package com.hetpatel.order_service.mapper;

import com.hetpatel.order_service.dto.OrderDto;
import com.hetpatel.order_service.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {
    Order toEntity(OrderDto orderDto);
    OrderDto toDto(Order order);
}
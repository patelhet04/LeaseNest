package com.hetpatel.order_service.service;

import com.hetpatel.order_service.dto.OrderDto;
import com.hetpatel.order_service.exception.ResourceNotFoundException;
import com.hetpatel.order_service.mapper.OrderMapper;
import com.hetpatel.order_service.model.Order;
import com.hetpatel.order_service.model.OrderStatus;
import com.hetpatel.order_service.repo.OrderRepo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.DataException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@Slf4j
public class OrderService {
    private final OrderRepo orderRepo;
    private final OrderMapper orderMapper;
    public OrderService(OrderRepo orderRepo, OrderMapper orderMapper) {
        this.orderRepo = orderRepo;
        this.orderMapper = orderMapper;
    }

    public List<OrderDto> getOrders(){
        try{
            log.info("Getting all orders");
            List<Order> orders = orderRepo.findAll();
            return orders.stream().map(orderMapper::toDto).toList();
        }catch(DataException e){
            log.error("Error while fetching orders", e);
            throw new RuntimeException("Failed to fetch orders");
        }
    }

    public OrderDto getOrder(Long id){
        try{
            log.info("Get Order by id: {}", id);
            Order order =  orderRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Order not found"));
            return orderMapper.toDto(order);
        }catch(DataException e){
            log.error("Error while fetching order", e);
            throw new RuntimeException("Failed to fetch order");
        }
    }

    public List<OrderDto> getOrdersByStatus(OrderStatus status){
        log.info("Get Orders by status: {}", status);
        List<Order> orders = orderRepo.findByOrderStatus(status);
        return orders.stream().map(orderMapper::toDto).toList();
    }
    
    public OrderDto createOrder(OrderDto orderDto) {
        try{
            log.info("Creating new order");
            Order order = orderMapper.toEntity(orderDto);
            order.setStatus(OrderStatus.CREATED);
            order.setExpiresAt(LocalDateTime.now().plusMinutes(20));
            Order savedOrder = orderRepo.save(order);
            return orderMapper.toDto(savedOrder);
        }
        catch(DataException e){
            log.error("Error while creating order",e);
            throw new RuntimeException("Error while creating order");
        }
    }

    public void cancelOrder(Long orderId){
        log.info("Cancelling order: {}", orderId);
        Order order = orderRepo.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order not found"));
        order.setStatus(OrderStatus.CANCELLED);
    }


    @Scheduled(fixedRate = 120000)
    public void checkForExpiredOrders(){
        log.info("Checking for expired orders");
        LocalDateTime now = LocalDateTime.now();
        List<OrderStatus> statuses = List.of(OrderStatus.CREATED, OrderStatus.PENDING);
        List<Order> expiredOrders = orderRepo.findAllByStatusInAndExpiresAtBefore(statuses,now);

        expiredOrders.forEach(order->{
            order.setStatus(OrderStatus.EXPIRED);
            orderRepo.save(order);
        });
    }
}

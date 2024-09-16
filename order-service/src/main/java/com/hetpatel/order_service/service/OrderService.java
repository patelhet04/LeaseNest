package com.hetpatel.order_service.service;

import com.hetpatel.order_service.exception.ResourceNotFoundException;
import com.hetpatel.order_service.model.Order;
import com.hetpatel.order_service.repo.OrderRepo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.DataException;
import org.springframework.stereotype.Service;

@Service
@Data
@Slf4j
public class OrderService {
    private final OrderRepo orderRepo;

    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public Order getOrder(Long id){
        try{
            log.info("Get Order by id: {}", id);
            return orderRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Order not found"));
        }catch(DataException e){
            log.error("Error while fetching order", e);
            throw new RuntimeException("Failed to fetch order");
        }
    }
    
    public Order createOrder(Order order) {
        try{
            log.info("Creating new order");
            return orderRepo.save(order);
        }
        catch(DataException e){
            log.error("Error while creating order",e);
            throw new RuntimeException("Error while creating order");
        }
    }
}

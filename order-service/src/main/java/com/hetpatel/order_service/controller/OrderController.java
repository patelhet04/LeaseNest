package com.hetpatel.order_service.controller;

import com.hetpatel.order_service.dto.OrderDto;
import com.hetpatel.order_service.model.Order;
import com.hetpatel.order_service.model.OrderStatus;
import com.hetpatel.order_service.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        log.info("Creating order");
        return ResponseEntity.ok(orderService.createOrder(orderDto));
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDto>> getOrders(){
        log.info("Get orders");
        return ResponseEntity.ok(orderService.getOrders());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long orderId){
        log.info("Retrieving order");
        return new ResponseEntity<>(orderService.getOrder(orderId), HttpStatus.OK);
    }

    @GetMapping("/orderByStatus/{orderStatus}")
    public ResponseEntity<List<OrderDto>> getOrdersByStatus(@PathVariable OrderStatus orderStatus){
        log.info("Get Orders by status: {}", orderStatus);
        return ResponseEntity.ok(orderService.getOrdersByStatus(orderStatus));
    }
}

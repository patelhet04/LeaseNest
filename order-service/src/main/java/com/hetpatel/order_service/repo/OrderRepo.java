package com.hetpatel.order_service.repo;

import com.hetpatel.order_service.model.Order;
import com.hetpatel.order_service.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {
    List<Order> findByOrderStatus(OrderStatus status);
}

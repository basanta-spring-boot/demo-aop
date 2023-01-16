package com.javatechie.service;

import com.javatechie.entity.Order;
import com.javatechie.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public Order saveOrder(Order order) {
        return repository.save(order);
    }

    public List<Order> getOrders() {
        return repository.findAll();
    }

    public Order getOrder(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }


    public Order updateOrder(Integer id, Order orderRequest) {
        Order existingOrder = getOrder(id);
        existingOrder.setName(orderRequest.getName());
        existingOrder.setCategory(orderRequest.getCategory());
        existingOrder.setPrice(orderRequest.getPrice());
        existingOrder.setQty(orderRequest.getQty());
        return repository.save(existingOrder);
    }
}

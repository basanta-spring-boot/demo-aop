package com.javatechie.controller;

import com.javatechie.dto.OrderRequestDTO;
import com.javatechie.entity.Order;
import com.javatechie.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping
    public Order saveOrder(@RequestBody OrderRequestDTO<Order> requestDTO) {
        return service.saveOrder(requestDTO.getPayload());
    }

    @GetMapping
    public List<Order> getOrders() {
        return service.getOrders();
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Integer id) {
        return service.getOrder(id);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Integer id, @RequestBody OrderRequestDTO<Order> requestDTO) {
        return service.updateOrder(id, requestDTO.getPayload());
    }
}

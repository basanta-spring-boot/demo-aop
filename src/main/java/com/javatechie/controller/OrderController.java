package com.javatechie.controller;

import com.javatechie.annotation.LogRequestAndResponse;
import com.javatechie.annotation.TrackExecutionTime;
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
    @LogRequestAndResponse
    @TrackExecutionTime
    public Order saveOrder(@RequestBody OrderRequestDTO<Order> requestDTO) {
        if (requestDTO.getPayload().getQty() < 1) {
            throw new RuntimeException("qty should be at least 1");
        }
        if (requestDTO.getPayload().getPrice() < 100) {
            throw new IllegalArgumentException("price should be greater than  100");
        }
        return service.saveOrder(requestDTO.getPayload());
    }

    @GetMapping
    @TrackExecutionTime
    public List<Order> getOrders() {
        return service.getOrders();
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Integer id) {
        return service.getOrder(id);
    }

    @PutMapping("/{id}")
    @LogRequestAndResponse
    public Order updateOrder(@PathVariable Integer id, @RequestBody OrderRequestDTO<Order> requestDTO) {
        return service.updateOrder(id, requestDTO.getPayload());
    }
}

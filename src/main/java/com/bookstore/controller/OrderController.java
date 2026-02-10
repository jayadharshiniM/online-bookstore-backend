package com.bookstore.controller;



import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.model.Order;
import com.bookstore.repository.OrderRepository;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("*")
public class OrderController {

    private final OrderRepository repo;

    public OrderController(OrderRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        order.setOrderDate(LocalDateTime.now());
        return repo.save(order);
    }

}

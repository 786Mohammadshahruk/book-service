package com.book.service.books.service;

import com.book.service.books.dao.OrderRepository;
import com.book.service.books.dto.AllOrders;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private OrderRepository orderRepository;

    public AdminService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;

    }
    public List<AllOrders> findOrders() {
        return orderRepository.findAll().stream().map(orderEntity -> new AllOrders(orderEntity.getOrderId(), orderEntity.getName(),orderEntity.getDate(),orderEntity.getAddress(),orderEntity.getCountry(),orderEntity.getAlternativeMobileNumber(),orderEntity.getOrder_value())).collect(Collectors.toList());
    }
}

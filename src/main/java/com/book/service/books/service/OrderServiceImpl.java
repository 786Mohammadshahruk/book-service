package com.book.service.books.service;

import com.book.service.books.dao.OrderRepository;
import com.book.service.books.dto.OrderRequest;
import com.book.service.books.entity.OrderEntity;
import com.book.service.books.records.ResponseOrder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderServiceImpl {

    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public ResponseOrder createOrder(OrderRequest orderRequest) {
        String orderId = UUID.randomUUID().toString();
        return new ResponseOrder(orderRepository.save(new OrderEntity(orderId, orderRequest.getBookId(), orderRequest.getAddress(), orderRequest.getPinCode(), orderRequest.getCountry(), orderRequest.getAlternativeMobileNumber())));
    }
}

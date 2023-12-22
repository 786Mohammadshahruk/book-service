package com.book.service.books.service;

import com.book.service.books.dao.OrderRepository;
import com.book.service.books.dto.Order;
import com.book.service.books.dto.OrderRequest;
import com.book.service.books.entity.OrderEntity;
import com.book.service.books.records.ResponseOrder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl {

    private OrderRepository orderRepository;
    private BookServiceImpl bookService;

    public OrderServiceImpl(OrderRepository orderRepository,BookServiceImpl bookService) {
        this.orderRepository = orderRepository;
        this.bookService = bookService;
    }

    public ResponseOrder createOrder(OrderRequest orderRequest) {
        String orderId = UUID.randomUUID().toString();
        bookService.updateInventory(orderRequest.getBookId(),orderRequest.getQuantity());
        return new ResponseOrder(orderRepository.save(new OrderEntity(orderId, orderRequest.getBookId(), orderRequest.getAddress(), orderRequest.getPinCode(), orderRequest.getCountry(), orderRequest.getAlternativeMobileNumber())));
    }

    public List<Order> findOrders() {
        return orderRepository.findAll().stream().map(orderEntity -> new Order(orderEntity.getOrderId(), orderEntity.getBookId(),orderEntity.getAddress(),orderEntity.getPinCode(),orderEntity.getCountry(),orderEntity.getMobileNumber())).collect(Collectors.toList());
    }
}

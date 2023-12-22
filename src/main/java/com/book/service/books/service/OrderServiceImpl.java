package com.book.service.books.service;

import com.book.service.books.dao.OrderRepository;
import com.book.service.books.dto.OrderRequest;
import com.book.service.books.entity.OrderEntity;
import com.book.service.books.records.ResponseOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.book.service.books.utils.TokenDecoder.decodeToken;

@Service
public class OrderServiceImpl {

    private OrderRepository orderRepository;
    private BookServiceImpl bookService;

    public OrderServiceImpl(OrderRepository orderRepository, BookServiceImpl bookService) {
        this.orderRepository = orderRepository;
        this.bookService = bookService;
    }

    public ResponseOrder createOrder(OrderRequest orderRequest, String token) throws JsonProcessingException {

        String orderId = UUID.randomUUID().toString();
        bookService.updateInventory(orderRequest.getBookId(), orderRequest.getQuantity());
        return new ResponseOrder(orderRepository
                .save(new OrderEntity(orderId, orderRequest.getBookId(), orderRequest.getAddress(), orderRequest.getPinCode(),
                        orderRequest.getCountry(), orderRequest.getMobileNumber(), decodeToken(token), LocalDateTime.now(),
                        orderRequest.getOrder_value())));
    }

}

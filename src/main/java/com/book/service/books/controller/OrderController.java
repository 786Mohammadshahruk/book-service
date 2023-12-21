package com.book.service.books.controller;

import com.book.service.books.dto.OrderRequest;
import com.book.service.books.records.ResponseOrder;
import com.book.service.books.service.OrderServiceImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class OrderController {
    private OrderServiceImpl orderServiceImpl;

    public OrderController(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }
    @PostMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseOrder> orderBook(@RequestBody OrderRequest orderRequest) {
        ResponseOrder responseBook = orderServiceImpl.createOrder(orderRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", responseBook.orderEntity().getOrderId());
        return new ResponseEntity<>(responseBook, headers, HttpStatus.CREATED);

    }
}

package com.book.service.books.dto;


import java.util.List;

public record OrderResponse(List<Order> orderEntityList) {
}

package com.book.service.books.dto;

import java.time.LocalDateTime;
import java.util.Date;

public record AllOrders(String orderId, String name, LocalDateTime date, String address, String country, String mobile_no,  Integer order_value) {
}

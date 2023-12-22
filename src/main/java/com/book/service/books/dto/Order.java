package com.book.service.books.dto;

import com.book.service.books.records.OrderedBooks;

import java.time.LocalDateTime;
import java.util.List;

public record Order(String orderId, String bookId, String address, String pinCode, String country, String alternativeMobileNumber, String name,
                    LocalDateTime date, List<OrderedBooks> ordered_books,Integer order_value) {
}

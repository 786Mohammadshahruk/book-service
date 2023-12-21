package com.book.service.books.dto;

public record Order(String orderId, String bookId, String address, String pinCode, String country, String alternativeMobileNumber) {
}

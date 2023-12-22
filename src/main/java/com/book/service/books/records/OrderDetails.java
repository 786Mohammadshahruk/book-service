package com.book.service.books.records;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDetails {
    private String order_id;
    private String name;
    private LocalDateTime date;
    private String address;
    private String country;
    private String mobile_no;
    private List<OrderedBooks> ordered_books;
    private Integer order_value;

    public OrderDetails(String order_id, String name, LocalDateTime date, String address, String country, String mobile_no, List<OrderedBooks> ordered_books, Integer order_value) {
        this.order_id = order_id;
        this.name = name;
        this.date = date;
        this.address = address;
        this.country = country;
        this.mobile_no = mobile_no;
        this.ordered_books = ordered_books;
        this.order_value = order_value;
    }
}

package com.book.service.books.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @Column(name = "order_id")
    private String orderId;

    @Column(name = "book_id")
    private String bookId;
    @Column(name = "address")
    private String address;
    @Column(name = "pin_code")
    private String pinCode;
    @Column(name = "country")
    private String country;
    @Column(name = "mobile_no")
    private String mobileNumber;
    private String name;
    @Column(name = "date")
    private LocalDateTime date;
    private Integer order_value;


    public OrderEntity(String orderId, String bookId, String address, String pinCode, String country, String mobileNumber, String name, LocalDateTime date, Integer order_value) {
        this.orderId = orderId;
        this.bookId = bookId;
        this.address = address;
        this.pinCode = pinCode;
        this.country = country;
        this.mobileNumber = mobileNumber;
        this.name = name;
        this.date = date;
        this.order_value = order_value;

    }

    public OrderEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime dateTime) {
        this.date = dateTime;
    }

    public Integer getOrder_value() {
        return order_value;
    }

    public void setOrder_value(Integer order_value) {
        this.order_value = order_value;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAlternativeMobileNumber() {
        return mobileNumber;
    }

    public void setAlternativeMobileNumber(String alternativeMobileNumber) {
        this.mobileNumber = alternativeMobileNumber;
    }
}

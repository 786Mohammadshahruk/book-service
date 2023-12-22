package com.book.service.books.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
    @Column(name = "mobile_number")
    private String mobileNumber;

    public OrderEntity(String orderId, String bookId, String address, String pinCode, String country, String mobileNumber) {
        this.orderId = orderId;
        this.bookId = bookId;
        this.address = address;
        this.pinCode = pinCode;
        this.country = country;
        this.mobileNumber = mobileNumber;

    }
    public OrderEntity() {}

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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String alternativeMobileNumber) {
        this.mobileNumber = alternativeMobileNumber;
    }
}

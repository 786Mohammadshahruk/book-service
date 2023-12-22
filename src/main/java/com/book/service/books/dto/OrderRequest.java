package com.book.service.books.dto;

public class OrderRequest {
    private String bookId;
    private String address;
    private String pinCode;
    private String country;
    private String mobileNumber;
    private Integer quantity;
    private String[] ordered_books;
    private Integer order_value;

    public OrderRequest(String bookId, String address, String pinCode, String country, String mobileNumber, Integer quantity, String[] ordered_books, Integer order_value) {
        this.bookId = bookId;
        this.address = address;
        this.pinCode = pinCode;
        this.country = country;
        this.mobileNumber = mobileNumber;
        this.quantity = quantity;
        this.ordered_books = ordered_books;
        this.order_value = order_value;
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

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String[] getOrdered_books() {
        return ordered_books;
    }

    public void setOrdered_books(String[] ordered_books) {
        this.ordered_books = ordered_books;
    }

    public Integer getOrder_value() {
        return order_value;
    }

    public void setOrder_value(Integer order_value) {
        this.order_value = order_value;
    }
}

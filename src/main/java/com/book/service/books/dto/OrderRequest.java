package com.book.service.books.dto;

public class OrderRequest {
    private String bookId;
    private String address;
    private String pinCode;
    private String country;
    private String alternativeMobileNumber;

    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getBookId() {
        return bookId;
    }

    public String getAddress() {
        return address;
    }


    public String getPinCode() {
        return pinCode;
    }
    public String getCountry() {
        return country;
    }

    public String getAlternativeMobileNumber() {
        return alternativeMobileNumber;
    }


}

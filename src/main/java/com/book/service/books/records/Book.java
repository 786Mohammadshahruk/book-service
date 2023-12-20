package com.book.service.books.records;

public record Book(String isbn, String bookName, String description, String author, Integer publicationYear,
                   String smallImageUrl, String largeImageUrl, double price, Integer numberOfAvailableBooks,
                   double rating) {

}
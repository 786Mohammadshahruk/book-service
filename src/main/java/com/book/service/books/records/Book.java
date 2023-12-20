package com.book.service.books.records;

public record Book(String bookId,String isbn, String bookName, String description, String author, Integer publicationYear,
                   String smallImageUrl, String largeImageUrl, Double price, Integer numberOfAvailableBooks,
                   double rating) {

}

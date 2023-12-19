package com.book.service.books.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

@Data
public class BooksModel {
    @CsvBindByName(column = "ISBN",required = true)
    private String isbn;
    @CsvBindByName(column = "name")
    private String  BookName;
    @CsvBindByName(column = "description")
    private String description;
    @CsvBindByName(column = "author")
    private String author;
    @CsvBindByName(column = "publicationYear")
    private Integer publicationYear;
    @CsvBindByName(column = "smallImageUrl")
    private Integer smallImageUrl;
    @CsvBindByName(column = "largeImageUrl")
    private Integer largeImageUrl;
    @CsvBindByName(column = "price")
    private Double price;
    @CsvBindByName(column = "numberOfAvailableBooks")
    private Integer numberOfAvailableBooks;
    @CsvBindByName(column = "rating")
    private Double rating;
}

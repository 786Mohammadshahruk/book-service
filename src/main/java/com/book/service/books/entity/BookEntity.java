package com.book.service.books.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book")
@NoArgsConstructor
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer bookId;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "book_name")
    private String bookName;
    @Column(name = "description")
    private String description;
    @Column(name = "author")
    private String author;
    @Column(name = "publication_year")
    private Integer publicationYear;
    @Column(name = "small_image_url")
    private String smallImageUrl;
    @Column(name = "large_image_url")
    private String largeImageUrl;
    @Column(name = "price")
    private Double price;
    @Column(name = "number_of_available_books")
    private Integer numberOfAvailableBooks;
    @Column(name = "rating")
    private Double rating;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getSmallImageUrl() {
        return smallImageUrl;
    }

    public void setSmallImageUrl(String smallImageUrl) {
        this.smallImageUrl = smallImageUrl;
    }

    public String getLargeImageUrl() {
        return largeImageUrl;
    }

    public void setLargeImageUrl(String largeImageUrl) {
        this.largeImageUrl = largeImageUrl;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNumberOfAvailableBooks() {
        return numberOfAvailableBooks;
    }

    public void setNumberOfAvailableBooks(Integer numberOfAvailableBooks) {
        this.numberOfAvailableBooks = numberOfAvailableBooks;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public BookEntity(String isbn, String bookName, String description, String author,
                      Integer publicationYear, String smallImageUrl, String largeImageUrl, double price, Integer numberOfAvailableBooks, double rating) {
        this.isbn = isbn;
        this.bookName = bookName;
        this.description = description;
        this.author = author;
        this.publicationYear = publicationYear;
        this.smallImageUrl = smallImageUrl;
        this.largeImageUrl = largeImageUrl;
        this.price = price;
        this.numberOfAvailableBooks = numberOfAvailableBooks;
        this.rating = rating;
    }
}
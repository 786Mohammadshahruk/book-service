package com.book.service.books.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class BookEntity {
    @Id
    @Column(name = "book_id")
    private String bookId;
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
    private double price;
    @Column(name = "number_of_available_books")
    private Integer numberOfAvailableBooks;
    @Column(name = "rating")
    private double rating;

    public BookEntity() {
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getNumberOfAvailableBooks() {
        return numberOfAvailableBooks;
    }

    public void setNumberOfAvailableBooks(Integer numberOfAvailableBooks) {
        this.numberOfAvailableBooks = numberOfAvailableBooks;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
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

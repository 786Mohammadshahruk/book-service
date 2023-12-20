package com.book.service.books.service;

import com.book.service.books.dao.BookRepository;
import com.book.service.books.entity.BookEntity;
import com.book.service.books.records.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public ResponseEntity<String> readCSVFile(MultipartFile file) throws IOException {
        try (BufferedReader stream = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            stream.lines().map(line -> line.split(",")).forEach(splitted -> {
                List<String> dataLine = Arrays.asList(splitted);
                String isbn = removeASCII(dataLine.get(0)).trim();
                String bookName = dataLine.get(1);
                String description = dataLine.get(2);
                String author = dataLine.get(3);
                Integer publicationYear = Integer.parseInt(dataLine.get(4));
                String smallImageUrl = dataLine.get(5);
                String largeImageUrl = dataLine.get(6);
                double price = Double.parseDouble(dataLine.get(7));
                Integer numberOfAvailableBooks = Integer.parseInt(dataLine.get(8));
                double rating = Double.parseDouble(dataLine.get(9));
                BookEntity bookEntity = new BookEntity(isbn, bookName, description, author, publicationYear, smallImageUrl, largeImageUrl, price, numberOfAvailableBooks, rating);
                try {
                    bookRepository.save(bookEntity);
                } catch (Exception e) {

                }
            });
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    public List<Book> bookList() {
        return bookRepository.findAll().stream().map(bookEntity -> new Book(bookEntity.getIsbn(), bookEntity.getBookName(), bookEntity.getDescription(), bookEntity.getAuthor(), bookEntity.getPublicationYear(), bookEntity.getSmallImageUrl(), bookEntity.getLargeImageUrl(), bookEntity.getPrice(), bookEntity.getNumberOfAvailableBooks(), bookEntity.getRating())).collect(Collectors.toList());
    }

    public static String removeASCII(String str) {
        return str.replaceAll("[^\\x00-\\x7F]", " ") // remove all non-ASCII characters
                .replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", " ") //remove all the ASCII control characters
                .replaceAll("\\p{C}", " "); //removes non-printable characters from Unicode
    }

    public Book book(String isbn) {
        BookEntity bookEntity = bookRepository.findByIsbn(isbn);
        return new Book(bookEntity.getIsbn(), bookEntity.getBookName(), bookEntity.getDescription(), bookEntity.getAuthor(), bookEntity.getPublicationYear(), bookEntity.getSmallImageUrl(), bookEntity.getLargeImageUrl(), bookEntity.getPrice(), bookEntity.getNumberOfAvailableBooks(), bookEntity.getRating());
    }
}

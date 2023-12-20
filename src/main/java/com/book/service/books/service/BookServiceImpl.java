package com.book.service.books.service;

import com.book.service.books.entity.BookEntity;
import com.book.service.books.records.Book;
import com.book.service.books.dao.BookRepository;
import com.book.service.books.dto.BooksModel;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public ResponseEntity<String> readCSVFile(MultipartFile file) throws IOException {

        String line = null;
        BufferedReader stream = null;

        try {
            stream = new BufferedReader(new InputStreamReader(file.getInputStream()));
            while ((line = stream.readLine()) != null) {
                String[] splitted = line.split(",");
                List<String> dataLine = new ArrayList<String>(splitted.length);
                for (String data : splitted)
                    dataLine.add(data);
                System.out.println(dataLine);

                String isbn = dataLine.get(0);
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
                    BookEntity bookEntityFromDB = bookRepository.save(bookEntity);
                } catch (Exception e) {

                }
            }
        } finally {
            if (stream != null)
                stream.close();
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    public List<Book> bookList() {
        return bookRepository.findAll()
                .stream()
                .map(bookEntity -> new Book(bookEntity.getIsbn(), bookEntity.getBookName(), bookEntity.getDescription(),
                        bookEntity.getAuthor(), bookEntity.getPublicationYear(), bookEntity.getSmallImageUrl(),
                        bookEntity.getLargeImageUrl(), bookEntity.getPrice(), bookEntity.getNumberOfAvailableBooks(),
                        bookEntity.getRating()))
                .collect(Collectors.toList());
    }
}

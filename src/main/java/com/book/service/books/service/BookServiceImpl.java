package com.book.service.books.service;

import com.book.service.books.dao.BookRepository;
import com.book.service.books.entity.BookEntity;
import com.book.service.books.records.Book;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

@Service
public class BookServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void saveBooks(InputStream inputStream) throws IOException, CsvException {
        List<BookEntity> bookList = parseCsvToBookEntity(inputStream);
        bookList.forEach(i -> {
            try {
                bookRepository.save(i);
            } catch (Exception exception) {
                logger.error("Failed to add Book for Book isbn : " + i.getIsbn());
            }
        });
    }

    public List<BookEntity> parseCsvToBookEntity(InputStream inputStream) throws CsvException, IOException {
        try (CSVReader reader = new CSVReader(new InputStreamReader(inputStream))) {
            List<String[]> csvData = reader.readAll();
            return convertCsvDataToObjects(csvData);
        }
    }

    private List<BookEntity> convertCsvDataToObjects(List<String[]> csvData) {
        return csvData.stream()
                .map(row -> {
                    String uuid = UUID.randomUUID().toString();
                    BookEntity csvObject = new BookEntity();
                    csvObject.setBookId(uuid);
                    csvObject.setIsbn(row[0]);
                    csvObject.setBookName(row[1]);
                    csvObject.setDescription(row[2]);
                    csvObject.setAuthor(row[3]);
                    csvObject.setPublicationYear(parseInt(row[4]));
                    csvObject.setSmallImageUrl(row[5]);
                    csvObject.setLargeImageUrl(row[6]);
                    csvObject.setPrice(parseFloat(row[7]));
                    csvObject.setNumberOfAvailableBooks(parseInt(row[8]));
                    csvObject.setRating(parseFloat(row[9]));

                    return csvObject;
                })
                .toList();
    }


    public List<Book> bookList() {
        return bookRepository.findAll().stream().map(bookEntity -> new Book(bookEntity.getIsbn(), bookEntity.getBookName(), bookEntity.getDescription(), bookEntity.getAuthor(), bookEntity.getPublicationYear(), bookEntity.getSmallImageUrl(), bookEntity.getLargeImageUrl(), bookEntity.getPrice(), bookEntity.getNumberOfAvailableBooks(), bookEntity.getRating())).collect(Collectors.toList());
    }

    public Book book(String bookId) {
        Optional<BookEntity> bookEntityOptional = bookRepository.findById(bookId);
        if (bookEntityOptional.isPresent()) {
            BookEntity bookEntity = bookEntityOptional.get();
            return new Book(bookEntity.getIsbn(), bookEntity.getBookName(), bookEntity.getDescription(), bookEntity.getAuthor(), bookEntity.getPublicationYear(), bookEntity.getSmallImageUrl(), bookEntity.getLargeImageUrl(), bookEntity.getPrice(), bookEntity.getNumberOfAvailableBooks(), bookEntity.getRating());
        }
        return null;

    }
}

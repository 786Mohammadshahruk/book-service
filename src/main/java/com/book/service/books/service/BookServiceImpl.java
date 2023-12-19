package com.book.service.books.service;

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
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public ResponseEntity<List<BooksModel>> uploadFile(MultipartFile file) {
        List<BooksModel> books = convertToModel(file, BooksModel.class);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    public <T> List<T> convertToModel(MultipartFile file, Class<T> responseType) {
        List<T> models;
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<?> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(responseType)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .build();
            models = (List<T>) csvToBean.parse();
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getCause().getMessage());
        }
        return models;
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

package com.book.service.books.controller;

import com.book.service.books.records.Book;
import com.book.service.books.records.BookResponse;
import com.book.service.books.service.BookServiceImpl;
import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin
public class BookController {

    private static final String ERROR_INVALID_FILE_FORMAT = "ERROR_INVALID_FILE_FORMAT";
    private static final String ERROR_MESSAGE_INVALID_FILE_FORMAT = "Uploaded file is not a CSV";
    public BookServiceImpl bookServiceImpl;

    public BookController(BookServiceImpl bookServiceImpl) {
        this.bookServiceImpl = bookServiceImpl;
    }

    @PostMapping("/books")
    public ResponseEntity<?> loadBooks(@RequestParam("file") MultipartFile file) throws IOException, CsvException {
        if (!isCSVFile(file)) {
            return createErrorResponse(ERROR_INVALID_FILE_FORMAT, ERROR_MESSAGE_INVALID_FILE_FORMAT);
        }
        bookServiceImpl.saveBooks(file.getInputStream());
        return new ResponseEntity<>("Loaded Books Successfully", HttpStatus.OK);
    }

    private boolean isCSVFile(MultipartFile file) {
        return file.getOriginalFilename() != null && file.getOriginalFilename().toLowerCase().endsWith(".csv");
    }

    private ResponseEntity<?> createErrorResponse(String errorCode, String errorMessage) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("errorCode", errorCode);
        errorResponse.put("errorMessage", errorMessage);
        return ResponseEntity.badRequest().body(errorResponse);
    }


    @GetMapping("/books")
    public ResponseEntity<BookResponse> getAllBooks() {
        return ResponseEntity.ok(new BookResponse(bookServiceImpl.bookList()));
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<Book> getBookDetails(@PathVariable("bookId") String isbn) {
        return ResponseEntity.ok(bookServiceImpl.book(isbn));
    }
}

package com.book.service.books.controller;

import com.book.service.books.records.BookResponse;
import com.book.service.books.service.BookServiceImpl;
import com.book.service.books.dto.BooksModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@RestController
public class BookController {
    public BookServiceImpl bookServiceImpl;

    public BookController(BookServiceImpl bookServiceImpl) {
        this.bookServiceImpl = bookServiceImpl;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFiles(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.getContentType().equals("text/csv")) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("errorCode","ERROR_INVALID_FILE_FORMAT");
            errorResponse.put("errorMessage", "Uploaded file is not a CSV");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        return bookServiceImpl.readCSVFile(file);
    }


    @GetMapping("/books")
    public ResponseEntity<BookResponse> getAllBooks() {
        return ResponseEntity.ok(new BookResponse(bookServiceImpl.bookList()));
    }


}

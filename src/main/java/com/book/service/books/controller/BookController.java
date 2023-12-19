package com.book.service.books.controller;

import com.book.service.books.records.BookResponse;
import com.book.service.books.service.BookServiceImpl;
import com.book.service.books.dto.BooksModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
public class BookController {
    public BookServiceImpl bookServiceImpl;

    public BookController(BookServiceImpl bookServiceImpl) {
        this.bookServiceImpl = bookServiceImpl;
    }

    @PostMapping("/upload")
    public ResponseEntity<List<BooksModel>> uploadFiles(@RequestParam("file") MultipartFile file) {
        return bookServiceImpl.uploadFile(file);
    }


    @GetMapping("books")
    public ResponseEntity<BookResponse> getAllBooks() {
        return ResponseEntity.ok(new BookResponse(bookServiceImpl.bookList()));
    }


}

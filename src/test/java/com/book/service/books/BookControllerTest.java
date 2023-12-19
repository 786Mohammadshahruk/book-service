package com.book.service.books;

import com.book.service.books.BookController;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(BookController.class)
@AutoConfigureMockMvc
public class BookControllerTest {

}

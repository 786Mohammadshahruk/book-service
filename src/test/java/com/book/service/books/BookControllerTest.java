package com.book.service.books;

import com.book.service.books.controller.BookController;
import com.book.service.books.records.Book;
import com.book.service.books.records.BookDetailsResponse;
import com.book.service.books.service.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.nio.charset.StandardCharsets;

import java.util.Collections;
import java.util.List;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookServiceImpl bookService;

    @Test
    void shouldGetNoBooksIfNotAvailableAny() throws Exception {
        when(bookService.bookList()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.books").isEmpty());
    }

    @Test
    void shouldGetBooksIfAvailable() throws Exception {

        Book book = new Book("01234X", "Java book", "Book description",
                "Amar", 2023, "imageUrl/img.img",
                "largeImageUrl.img", 100.55, 1, 4.5F);


        when(bookService.bookList()).thenReturn(List.of(book));

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.books[0].bookName")
                        .value("Java book"))
                .andExpect(jsonPath("$.books[0].author")
                        .value("Amar"));

    }


    @Test
    void shouldReturnOkIfFileIsCSV() throws Exception {
        byte[] content = "header1,header2\nvalue1,value2".getBytes();
        MockMultipartFile multipartFile = new MockMultipartFile(
                "file",
                "file.csv",
                "text/csv",
                content
        );

        mockMvc.perform(MockMvcRequestBuilders.multipart("/upload")
                        .file(multipartFile))
                .andExpect(status().isCreated());

    }

    @Test
    void shouldReturnBadRequestForNonCSVFile() throws Exception {

        byte[] content = "This is not a CSV file".getBytes(StandardCharsets.UTF_8);
        MockMultipartFile multipartFile = new MockMultipartFile(
                "file",
                "file.txt",
                "text/plain",
                content
        );
        mockMvc.perform(MockMvcRequestBuilders.multipart("/upload")
                        .file(multipartFile))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value("ERROR_INVALID_FILE_FORMAT"))
                .andExpect(jsonPath("$.errorMessage").value("Uploaded file is not a CSV"));
    }

    @Test
    void shouldGetNoBooksIfNotAvailableAnyForSpecifiedIsbn() throws Exception {
        when(bookService.book("123456")).thenReturn(null);

        mockMvc.perform(get("/book/{isbn}",123456))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.books").isEmpty());
    }

    @Test
    public void shouldReturnBookDetailsForMentionedISBN() throws Exception {
        Book book = new Book("123456", "Java book", "Book description",
                "Amar", 2023, "imageUrl/img.img",
                "largeImageUrl.img", 100.55, 1, 4.5F);
        BookDetailsResponse mockResponse = new BookDetailsResponse(book);

        when(bookService.book("123456")).thenReturn(book);


        mockMvc.perform(get("/book/{isbn}", "123456"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.books.author").value(book.author()))
                .andReturn();
    }
}

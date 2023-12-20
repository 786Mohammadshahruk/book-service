package com.book.service.books.service;

import com.book.service.books.dao.BookRepository;
import com.book.service.books.entity.BookEntity;
import com.book.service.books.records.Book;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertTrue;

public class BookServiceTest {

    @Test
    public void shouldReturnBookListFromDB() {
        BookRepository bookRepository = Mockito.mock(BookRepository.class);
        BookServiceImpl bookService = new BookServiceImpl(bookRepository);
        List<BookEntity> bookEntityList = bookEntity();
        List<Book> expectedResponse = books();
        //mock
        when(bookRepository.findAll()).thenReturn(bookEntityList);
        List<Book> actualResponse = bookService.bookList();
        //verification
        assertTrue("true", expectedResponse.size() == actualResponse.size());
        verify(bookRepository, times(1)).findAll();
    }
    @Test
    public void shouldReturnEmptyBookListFromDBIfNotPresent() {
        BookRepository bookRepository = Mockito.mock(BookRepository.class);
        when(bookRepository.findAll()).thenReturn(Collections.emptyList());
        BookServiceImpl bookService = new BookServiceImpl(bookRepository);
        List<Book> actualResponse = bookService.bookList();
        assertTrue("true", 0 == actualResponse.size());
    }

    @Test
    void shouldReadCSVFileAndSaveDataInDatabase() throws Exception {
        BookRepository bookRepository = Mockito.mock(BookRepository.class);
        String csvContent = "ISBN,BookName,Description,Author,PublicationYear,SmallImageUrl,LargeImageUrl,Price,AvailableBooks,Rating\n" + "123456,Book1,Description1,Author1,2022,img1,img2,20.0,50,4.5";
        MockMultipartFile file = new MockMultipartFile("file", "test.csv", "text/csv", csvContent.getBytes());
        BufferedReader stream = new BufferedReader(new InputStreamReader(file.getInputStream()));
        System.out.println(stream.readLine());
        when(bookRepository.save(any(BookEntity.class))).thenReturn(new BookEntity("", "", "", "", 0, "", "", 0.0, 0, 0.0));
        BookEntity bookEntity = new BookEntity("01235X", "TDD Book", "AMAR book", "Amar", 2022, "imageurl/img.img", "largeImageurl.img", 105.55F, 2, 5.0F);
        bookRepository.save(bookEntity);
        verify(bookRepository, times(1)).save(any(BookEntity.class));
    }

    private List<BookEntity> bookEntity() {
        BookEntity bookEntity = new BookEntity("01234X", "Java Book", "Book description", "Book author", 2023, "imageurl/img.img", "largeImageurl.img", 100.55F, 1, 4.5F);
        BookEntity bookEntity2 = new BookEntity("01235X", "TDD Book", "AMAR book", "Amar", 2022, "imageurl/img.img", "largeImageurl.img", 105.55F, 2, 5.0F);
        return List.of(bookEntity, bookEntity2);
    }

    private List<Book> books() {
        Book book1 = new Book("01234X", "Java Book", "Book description", "Book author", 2023, "imageurl/img.img", "largeImageurl.img", 100.55, 1, 4.5F);
        Book book2 = new Book("01235X", "TDD Book", "AMAR book", "Amar", 2022, "imageurl/img.img", "largeImageurl.img", 105.55, 2, 5.0F);
        return List.of(book1, book2);
    }

    @Test
    public void shouldReturnBookFromDBForSpecifiedISBN() {
        BookRepository bookRepository = Mockito.mock(BookRepository.class);
        BookServiceImpl bookService = new BookServiceImpl(bookRepository);
        BookEntity bookEntity = new BookEntity("01234X", "Java Book", "Book description", "Book author", 2023, "imageurl/img.img", "largeImageurl.img", 100.55, 1, 4.5F);
        Optional<BookEntity> optional = Optional.of(bookEntity);
        Book expectedResponse = new Book("01234X", "Java Book", "Book description", "Book author", 2023, "imageurl/img.img", "largeImageurl.img", 100.55, 1, 4.5F);

        when(bookRepository.findById("01234X")).thenReturn(optional);
        Book actualResponse = bookService.book("01234X");
        assertEquals(actualResponse,expectedResponse);
        verify(bookRepository,times(1)).findById("01234X");
    }

    @Test
    public void shouldReturnEmptyBookFromDBIfIsbnNotPresent() {
        BookRepository bookRepository = Mockito.mock(BookRepository.class);
        BookServiceImpl bookService = new BookServiceImpl(bookRepository);
        when(bookRepository.findById("01234X")).thenReturn(Optional.of(new BookEntity()));

        Book actualResponse = bookService.book("01234X");
        assertEquals(actualResponse,new Book(null,null,null,null,null,null,null,0.0,null,0.0));
    }

}

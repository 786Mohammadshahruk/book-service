package com.book.service.books.dao;

import com.book.service.books.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface BookRepository extends JpaRepository<BookEntity,Integer>{
   BookEntity findByIsbn(String parseInt);
}

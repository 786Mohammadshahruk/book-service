package com.book.service.books.dao;

import com.book.service.books.entity.BookEntity;
import com.book.service.books.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,String> {


}

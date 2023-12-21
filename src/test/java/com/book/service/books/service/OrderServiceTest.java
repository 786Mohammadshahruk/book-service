package com.book.service.books.service;

import com.book.service.books.dao.OrderRepository;
import com.book.service.books.dto.Order;
import com.book.service.books.dto.OrderRequest;
import com.book.service.books.entity.OrderEntity;
import com.book.service.books.records.ResponseOrder;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @Test
    public void testCreateOrder() {
        OrderRepository orderRepository = mock(OrderRepository.class);
        BookServiceImpl bookService = mock(BookServiceImpl.class);
        OrderServiceImpl orderService = new OrderServiceImpl(orderRepository, bookService);
        OrderRequest orderRequest = new OrderRequest(null, "101", "2", "Address1", "12345", 1);
        OrderEntity savedOrderEntity = new OrderEntity(null, orderRequest.getBookId(), orderRequest.getAddress(), orderRequest.getPinCode(), orderRequest.getCountry(), orderRequest.getAlternativeMobileNumber());
        doNothing().when(bookService).updateInventory(orderRequest.getBookId(), orderRequest.getQuantity());
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(savedOrderEntity);
        ResponseOrder result = orderService.createOrder(orderRequest);
        assertNotNull(result);
        assertEquals(savedOrderEntity.getOrderId(), result.orderEntity().getOrderId());
        verify(bookService, times(1)).updateInventory(orderRequest.getBookId(), orderRequest.getQuantity());
    }

    @Test
    public void testFindOrders() {
        OrderRepository orderRepository = mock(OrderRepository.class);
        BookServiceImpl bookService = mock(BookServiceImpl.class);
        OrderServiceImpl orderService = new OrderServiceImpl(orderRepository, bookService);
        OrderEntity orderEntity1 = new OrderEntity("1", "101", "Address1", "12345", "Country1", "1234567890");
        OrderEntity orderEntity2 = new OrderEntity("2", "102", "Address2", "67890", "Country2", "9876543210");
        when(orderRepository.findAll()).thenReturn(Arrays.asList(orderEntity1, orderEntity2));
        List<Order> result = orderService.findOrders();
        assertEquals(2, result.size());
        Order order1 = result.get(0);
        assertEquals("1", order1.orderId());
        assertEquals("101", order1.bookId());
        assertEquals("Address1", order1.address());
        assertEquals("12345", order1.pinCode());
        assertEquals("Country1", order1.country());
        assertEquals("1234567890", order1.alternativeMobileNumber());

        Order order2 = result.get(1);
        assertEquals("2", order2.orderId());
        assertEquals("102", order2.bookId());
        assertEquals("Address2", order2.address());
        assertEquals("67890", order2.pinCode());
        assertEquals("Country2", order2.country());
        assertEquals("9876543210", order2.alternativeMobileNumber());
        verify(orderRepository, times(1)).findAll();
    }
}

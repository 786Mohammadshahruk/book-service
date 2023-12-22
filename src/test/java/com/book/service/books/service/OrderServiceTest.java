package com.book.service.books.service;

import com.book.service.books.dao.OrderRepository;
import com.book.service.books.dto.OrderRequest;
import com.book.service.books.entity.OrderEntity;
import com.book.service.books.records.ResponseOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @Test
    public void testCreateOrder() throws JsonProcessingException {
        OrderRepository orderRepository = mock(OrderRepository.class);
        BookServiceImpl bookService = mock(BookServiceImpl.class);
        OrderServiceImpl orderService = new OrderServiceImpl(orderRepository, bookService);
        OrderRequest orderRequest = new OrderRequest(null, "101", "2", "Address1", "12345", 1, new String[5], 100);
        OrderEntity savedOrderEntity = new OrderEntity(null, orderRequest.getBookId(), orderRequest.getAddress(), orderRequest.getPinCode(), orderRequest.getCountry(), orderRequest.getMobileNumber(),"test Name", LocalDateTime.now(),orderRequest.getOrder_value());
        doNothing().when(bookService).updateInventory(orderRequest.getBookId(), orderRequest.getQuantity());
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(savedOrderEntity);
        ResponseOrder result = orderService.createOrder(orderRequest ,"eyJraWQiOiJkZWZhdWx0IiwidHlwIjoiSldUIiwiYWxnIjoiUlMyNTYifQ.eyJuYmYiOjE3MDMyMjMzNDUsInJvbGUiOiJ1c2VyIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDkwL2RlZmF1bHQiLCJleHAiOjE3MDMyMjY5NDUsImlhdCI6MTcwMzIyMzM0NSwianRpIjoiMjFjODBhNDYtNjJmMS00ODEyLWI2ZjUtM2IyYmEzZGNjNTc1In0.dQ2VDHtINEc1zZD-ProKSskJagG2IRDDV3SJ9eIq6eOIAnHcKYaJdvY-iolY6YDLIAodz0qICGOA56w5MwONEJ7XNzFMMxaqfGf7xWxr5irEq98PBO6mqQjdZRQ4xv24qKGwVz86Jv663o_xuvv43NdPIsKyfTuBCkLfSQmae0CTey70pDuP6rHArAhsvIQeVaAk_YCSWBQ1fB0C7cEZeyc-wtopsJW-SUb1ghEbYwOsD-S9BqPjrPsq8qtcl5xyvh8jBuJiit542iL-jIH__L6wwysYrSzKAZTQbTyhJXILbkrUhZmsHznXNGzeUY18FhybqOm5uLV5Xfx10t6wog");
        assertNotNull(result);
        assertEquals(savedOrderEntity.getOrderId(), result.orderEntity().getOrderId());
        verify(bookService, times(1)).updateInventory(orderRequest.getBookId(), orderRequest.getQuantity());
    }

    /*@Test
    public void testFindOrders() {
        OrderRepository orderRepository = mock(OrderRepository.class);
        BookServiceImpl bookService = mock(BookServiceImpl.class);
        OrderServiceImpl orderService = new OrderServiceImpl(orderRepository, bookService);
        OrderEntity orderEntity1 = new OrderEntity("1", "101", "Address1", "12345", "Country1", "1234567890","test Name", LocalDateTime.now(),10);
        OrderEntity orderEntity2 = new OrderEntity("2", "102", "Address2", "67890", "Country2", "9876543210","test Name", LocalDateTime.now(),10);
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
    }*/
}

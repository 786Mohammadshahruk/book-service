package com.book.service.books.controller;

import com.book.service.books.dto.Order;
import com.book.service.books.dto.OrderRequest;
import com.book.service.books.entity.OrderEntity;
import com.book.service.books.records.ResponseOrder;
import com.book.service.books.service.OrderServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
@AutoConfigureMockMvc

public class OrderControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    OrderServiceImpl orderService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testOrderBook() throws Exception {
        OrderRequest orderRequest = new OrderRequest("101", "Address 1", "987654", "india", "98765432", 1,new String[5],10);
        ResponseOrder responseOrder = new ResponseOrder(new OrderEntity("101", "101", "Address 1", "987654", "india", "98765432","test Name", LocalDateTime.now(),10));

        Mockito.when(orderService.createOrder(any(OrderRequest.class),anyString())).thenReturn(responseOrder);
        mockMvc.perform(MockMvcRequestBuilders.post("/order")
                        .header("Authorization","")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(orderRequest)))
                .andExpect(status().isCreated());
        Mockito.verify(orderService,Mockito.times(1)).createOrder(any(OrderRequest.class),anyString());
    }

    /*@Test
    public void testFindOrders() throws Exception {

        Order order = new Order("1", "101", "test Address", "833201", "India", "9876543210");
        Mockito.when(orderService.findOrders()).thenReturn(List.of(order));

        mockMvc.perform(MockMvcRequestBuilders.get("/orders"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        Mockito.verify(orderService, Mockito.times(1)).findOrders();
    }*/
}
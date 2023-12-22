package com.book.service.books.controller;


import com.book.service.books.dto.AllOrderResponse;
import com.book.service.books.dto.OrderResponse;
import com.book.service.books.records.BookResponse;
import com.book.service.books.records.ResponseOrder;
import com.book.service.books.service.AdminService;
import com.book.service.books.service.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin
public class AdminController {
    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin/orders")
    public ResponseEntity getAllOrders() {
        return ResponseEntity.ok(new AllOrderResponse(adminService.findOrders()));
    }
}

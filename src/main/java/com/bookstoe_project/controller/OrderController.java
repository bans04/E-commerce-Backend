package com.bookstoe_project.controller;

import com.bookstoe_project.dto.ResponseDto;
import com.bookstoe_project.entity.Order;
import com.bookstoe_project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity createOrder(@RequestParam String token){
        Order order = orderService.createOrder(token);
        ResponseDto responseDto = new ResponseDto("Order created successfully", order);
        return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/userwishlist")
    public ResponseEntity userWishlist(@RequestParam String token){
        Order order = orderService.userOrder(token);
        ResponseDto responseDto = new ResponseDto("Order created successfully", order);
        return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/cancelorder")
    public ResponseEntity cancelOrder(@RequestParam String token){
        Order order = orderService.cancelOrder(token);
        ResponseDto responseDto = new ResponseDto("Order created successfully", order);
        return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
    }
}

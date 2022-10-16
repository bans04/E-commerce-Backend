package com.bookstoe_project.controller;

import com.bookstoe_project.dto.ResponseDto;
import com.bookstoe_project.entity.Book;
import com.bookstoe_project.entity.Cart;
import com.bookstoe_project.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:4200")
public class CartController {
    @Autowired
    CartService cartService;


    @PostMapping("/add")
    public ResponseEntity<ResponseDto> addToCart(@RequestParam long bookId, String token){
        Cart cart = cartService.addToCart(bookId, token);
        ResponseDto responseDto = new ResponseDto("User book Added successfully in cart", cart);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/remove")
    public  ResponseEntity<ResponseDto> removeBookFromCart(@RequestParam long bookId, String token){
        Book bookData = cartService.removeBookFromCart(bookId, token);
        ResponseDto responseDto = new ResponseDto("book successfully removed from cart of bookId " + bookId, bookData);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

//    @GetMapping("/cart")
//    public ResponseEntity<ResponseDto> getCartById(long cartId){
//        Cart cart = cartService.getCartById(cartId);
//        ResponseDto responseDto = new ResponseDto("Your cart detail is here ", cart);
//        return new ResponseEntity<>(responseDto, HttpStatus.OK);
//    }

    @GetMapping("/usercart")
    public ResponseEntity<ResponseDto> getCardByUser(@RequestParam String token){
        Cart cart = cartService.getCardByUser(token);
        ResponseDto responseDto = new ResponseDto("Your cart detail is here:", cart);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

//    @GetMapping("/carts")
//    public ResponseEntity<ResponseDto> getCarts(){
//        List<Cart> cart = cartService.getAllCart();
//        ResponseDto responseDto = new ResponseDto("Your cart detail is here ", cart);
//        return new ResponseEntity<>(responseDto, HttpStatus.OK);
//    }


}

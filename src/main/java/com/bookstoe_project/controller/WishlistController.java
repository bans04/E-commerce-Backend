package com.bookstoe_project.controller;

import com.bookstoe_project.dto.ResponseDto;
import com.bookstoe_project.entity.Wishlist;
import com.bookstoe_project.repository.WishlistRepository;
import com.bookstoe_project.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishlist")
@CrossOrigin(origins = "http://localhost:4200")
public class WishlistController {

    @Autowired
    WishlistService wishlistService;

    @PostMapping("/addbook")
    public ResponseEntity<ResponseDto> addBookToWishList(@RequestParam long bookId, String token){
        Wishlist wishList = wishlistService.addBookToWishList(bookId, token);
        ResponseDto responseDto = new ResponseDto("Book added successfully in wishList", wishList);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/removebook")
    public ResponseEntity<ResponseDto> removeBookFromWishList(@RequestParam long bookId, String token){
        Wishlist wishList = wishlistService.removeBookFromWishList(bookId, token);
        ResponseDto responseDto = new ResponseDto("Book added successfully in wishList", wishList);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/userwishlist")
    public ResponseEntity<ResponseDto> userWishlist(@RequestParam  String token){
        Wishlist wishList = wishlistService.getWishListByUser(token);
        ResponseDto responseDto = new ResponseDto("Book added successfully in wishList", wishList);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}

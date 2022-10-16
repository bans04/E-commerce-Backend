package com.bookstoe_project.service;

import com.bookstoe_project.entity.Book;
import com.bookstoe_project.entity.Cart;
import com.bookstoe_project.entity.UserData;
import com.bookstoe_project.exception.BookNotFound;
import com.bookstoe_project.exception.NoAccountFound;
import com.bookstoe_project.exception.UserNotFoundException;
import com.bookstoe_project.repository.BookRepository;
import com.bookstoe_project.repository.CartRepository;
import com.bookstoe_project.repository.UserRepository;
import com.bookstoe_project.utility.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TokenUtility tokenUtility;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;

    public Cart addToCart(long bookId, String token){
        long userId = tokenUtility.decodeToken(token);
        Optional<UserData> userData = userRepository.findById(userId);

        if(userData.isPresent()){
            Cart existingCart = userData.get().getCart();
            Book book = bookRepository.findById(bookId).get();
//            existingCart.getBookSet().add(book);
//            Set<Book> books = new HashSet<>();
//            books.add(book);
            existingCart.getBookSet().add(book);
            existingCart.setTotalPrice(totalPrice(existingCart.getBookSet()));
            existingCart.setTotalBooks(totalBooks(existingCart.getBookSet()));
            userData.get().setCart(existingCart);
            existingCart.setUserData(userData.get());
            userData.get().setCart(existingCart);
            userRepository.save(userData.get());
//            Set<Book>books = existingCart.getBookSet();
            return cartRepository.save(existingCart);
        }else {
            throw new UserNotFoundException("Invalid token");
        }
    }

    public Book removeBookFromCart(long bookId, String token) {
        long userId = tokenUtility.decodeToken(token);
        UserData userData = userRepository.findById(userId).get();
        if(userData != null){
            Set bookSet = userData.getCart().getBookSet();
            Book book = bookRepository.findById(bookId).get();
            if(bookSet.contains(book)){
                bookSet.remove(book);
                userData.getCart().setTotalBooks(totalBooks(bookSet));
                userData.getCart().setTotalPrice(totalPrice(bookSet));
                userRepository.save(userData);
                cartRepository.save(userData.getCart());
                return book;
            }else {
                throw new BookNotFound("No book found with this bookId " + bookId);
            }
        }else {
            throw new UserNotFoundException("User not found with this userId " + userId);
        }
    }

    public Cart getCardByUser(String token) {
        Long userId = tokenUtility.decodeToken(token);
        UserData userData = userRepository.findById(userId).get();
        if(userData != null){
            return userData.getCart();
        }else {
            throw new UserNotFoundException("User not present with this userId " + userId);
        }
    }

    public Cart updateCart(String token, Long bookId, Long quantity){
        Long userId = tokenUtility.decodeToken(token);
        Optional<UserData> userData = userRepository.findById(userId);

        if(userData.isPresent()){

        }else {
            throw new  UserNotFoundException("Invalid token");
        }
        return null;
    }

    public Double totalPrice(Set<Book> books){
        Double totalCartPrice = 0.0;
       for(Book book: books){
           totalCartPrice += book.getPrice();
       }
       return totalCartPrice;
    }

    public Long totalBooks(Set<Book> books){
        Long totalCartBooks = Long.valueOf(0);
        for(Book book: books){
            totalCartBooks += 1;
        }
        return totalCartBooks;
    }
}

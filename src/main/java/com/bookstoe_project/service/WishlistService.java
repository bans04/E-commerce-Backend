package com.bookstoe_project.service;

import com.bookstoe_project.entity.Book;
import com.bookstoe_project.entity.UserData;
import com.bookstoe_project.entity.Wishlist;
import com.bookstoe_project.exception.BookNotFound;
import com.bookstoe_project.exception.UserNotFoundException;
import com.bookstoe_project.repository.BookRepository;
import com.bookstoe_project.repository.UserRepository;
import com.bookstoe_project.repository.WishlistRepository;
import com.bookstoe_project.utility.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class WishlistService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    WishlistRepository wishlistRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenUtility tokenUtility;

    public Wishlist addBookToWishList(long bookId, String token) {
        long userId = tokenUtility.decodeToken(token);
        Optional<UserData> userData = userRepository.findById(userId);
        if(userData.isPresent()){
            Book bookData = bookRepository.findById(bookId).get();
            Wishlist wishlist = userData.get().getWishlist();
            wishlist.getWishlistBook().add(bookData);
            wishlist.setUserData(userData.get());
            userData.get().setWishlist(wishlist);
            wishlistRepository.save(wishlist);
            userRepository.save(userData.get());
            return wishlist;
        }else {
            throw new UserNotFoundException("Invalid Token");
        }
    }

    public Wishlist removeBookFromWishList(long bookId, String token) {
        long userId = tokenUtility.decodeToken(token);
        Optional<UserData> userData = userRepository.findById(userId);
        if (userData.isPresent()) {
            Wishlist wishlist = userData.get().getWishlist();
            Book book = bookRepository.findById(bookId).get();
            wishlist.getWishlistBook().remove(book);
            userRepository.save(userData.get());
            bookRepository.save(book);
            return wishlistRepository.save(wishlist);
        } else {
            throw new UserNotFoundException("Invalid Token");
        }
    }

    public Wishlist getWishListByUser(String token) {
        long userId = tokenUtility.decodeToken(token);
        Optional<UserData> userData = userRepository.findById(userId);
        if (userData.isPresent()) {
            return userData.get().getWishlist();
        } else {
            throw new UserNotFoundException("User not found with this userId " + userId);
        }
    }
}

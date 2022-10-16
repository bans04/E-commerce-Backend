package com.bookstoe_project.service;

import com.bookstoe_project.entity.*;
import com.bookstoe_project.exception.UserNotFoundException;
import com.bookstoe_project.repository.CartRepository;
import com.bookstoe_project.repository.OrderRepository;
import com.bookstoe_project.repository.UserRepository;
import com.bookstoe_project.utility.TokenUtility;
import net.bytebuddy.pool.TypePool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    TokenUtility tokenUtility;

    public Order createOrder(String token) {
        long userId = tokenUtility.decodeToken(token);
        UserData userData = userRepository.findById(userId).get();
        if(userData != null){
           Order order = new Order();
           Cart cart = userData.getCart();
           order.setOrderPrice(userData.getCart().getTotalPrice());
           order.setQuantity(userData.getCart().getTotalBooks());
           order.setUserData(userData);
           order.setLocalDate(LocalDate.now());
//           order.setOrderBookList(userData.getCart().getBookSet());
            order.getOrderBookList().addAll(userData.getCart().getBookSet());
//           userData.getOrder().add(order);
            userData.setOrder(order);
            orderRepository.save(order);
           userRepository.save(userData);
           cartRepository.save(userData.getCart());
//            userData.getCart().setBookSet(null);
            return order;
        }else {
            throw new UserNotFoundException("User not found with this userId" + userId);
        }
    }

    public Order userOrder(String token) {
        long userId = tokenUtility.decodeToken(token);
        Optional<UserData> userData = userRepository.findById(userId);
        if (userData.isPresent()) {
//            Set<Order> orders = new HashSet<>();
//            for(Order order: userData.get().getOrder()){
//                orders.add(order);
//            }
//            return userData.get().getOrder();
            return userData.get().getOrder();
        } else {
            throw new UserNotFoundException("User not found with this userId" + userId);
        }
    }

    public Order cancelOrder(String token){
        long userId = tokenUtility.decodeToken(token);
        Optional<UserData> userData = userRepository.findById(userId);
        if (userData.isPresent()) {
            userData.get().getOrder().setCancel(true);
            userRepository.save(userData.get());
            orderRepository.save(userData.get().getOrder());
            return userData.get().getOrder();
        } else {
            throw new UserNotFoundException("User not found with this userId" + userId);
        }
    }
}

package com.bookstoe_project.service;

import com.bookstoe_project.dto.ShippingAddressDto;
import com.bookstoe_project.dto.UserDto;
import com.bookstoe_project.entity.ShippingAddress;
import com.bookstoe_project.entity.UserData;
import com.bookstoe_project.exception.UserNotFoundException;
import com.bookstoe_project.repository.OrderRepository;
import com.bookstoe_project.repository.ShippingAddressRepository;
import com.bookstoe_project.repository.UserRepository;
import com.bookstoe_project.utility.TokenUtility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingAddressService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    TokenUtility tokenUtility;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ShippingAddressRepository shippingAddressRepository;


    public ShippingAddress saveShippingAddress(ShippingAddressDto shippingAddressDto, String token) {
        long userId = tokenUtility.decodeToken(token);
        UserData userData = userRepository.findById(userId).get();

        if(userData != null){
            ShippingAddress shippingAddress = modelMapper.map(shippingAddressDto, ShippingAddress.class);
//            userData.getShippingAddresses().add(shippingAddress);
            userData.setShippingAddress(shippingAddress);
            userRepository.save(userData);
            userData.getOrder().setShippingAddress(userData.getShippingAddress());
            orderRepository.save(userData.getOrder());
            return shippingAddressRepository.save(shippingAddress);
        }else {
            throw new UserNotFoundException("Invalid Token");
        }

    }
}

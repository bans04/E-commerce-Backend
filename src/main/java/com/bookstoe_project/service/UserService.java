package com.bookstoe_project.service;

import com.bookstoe_project.dto.UserDto;
import com.bookstoe_project.dto.UserLogin;
import com.bookstoe_project.dto.UserResponseDto;
import com.bookstoe_project.entity.Cart;
import com.bookstoe_project.entity.UserData;
import com.bookstoe_project.entity.Wishlist;
import com.bookstoe_project.exception.InvalidPassword;
import com.bookstoe_project.exception.UserNotFoundException;
import com.bookstoe_project.repository.UserRepository;
import com.bookstoe_project.utility.TokenUtility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    TokenUtility tokenUtility;

    @Autowired
    EmailService emailService;

    public UserResponseDto saveUser(UserDto userDto){
        UserData userData = modelMapper.map(userDto, UserData.class);
        Cart cart = new Cart();
        cart.setUserData(userData);
        userData.setCart(cart);
        Wishlist wishlist = new Wishlist();
        userData.setWishlist(wishlist);
        wishlist.setUserData(userData);
        userRepository.save(userData);

        UserResponseDto userResponseDto = modelMapper.map(userData, UserResponseDto.class);
        return userResponseDto;
    }

    public UserResponseDto userLogin(UserLogin userLogin) {
        UserData userData = userRepository.findByEmail(userLogin.getEmail());

        if(userData != null){
            if(userData.getPassword().equals(userLogin.getPassword())){
                String token = tokenUtility.createToken(userData.getUserId());
                UserResponseDto userResponseDto = new UserResponseDto();
                userResponseDto.setFullName(userData.getFullName());
                userResponseDto.setMobileNo(userData.getMobileNumber());
                userResponseDto.setToken(token);
                return userResponseDto;
            }else {
                throw new InvalidPassword("Password is incorrect");
            }
        }else {
            throw new UserNotFoundException("Please register first and then try to login");
        }
    }
}

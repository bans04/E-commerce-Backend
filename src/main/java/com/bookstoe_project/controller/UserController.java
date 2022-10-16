package com.bookstoe_project.controller;

import com.bookstoe_project.dto.ResponseDto;
import com.bookstoe_project.dto.UserDto;
import com.bookstoe_project.dto.UserLogin;
import com.bookstoe_project.dto.UserResponseDto;
import com.bookstoe_project.entity.UserData;
import com.bookstoe_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/registration")
    ResponseEntity<ResponseDto> registerUser(@RequestBody UserDto userDto){
        UserResponseDto userDto1 = userService.saveUser(userDto);
        ResponseDto responseDto = new ResponseDto("User registration completed successfully ", userDto1);
        return new  ResponseEntity<>(responseDto, HttpStatus.OK);
    }

//    @GetMapping("/login")
    @PostMapping("/login")
    ResponseEntity<ResponseDto> userLogin(@RequestBody UserLogin userLogin){
//        UserData userData = userService.userLogin(userLogin);
//        String token = userService.userLogin(userLogin);
        UserResponseDto userResponseDto = userService.userLogin(userLogin);
        ResponseDto responseDto = new ResponseDto("Login Successfully", userResponseDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}

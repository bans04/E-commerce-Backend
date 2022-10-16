package com.bookstoe_project.controller;

import com.bookstoe_project.dto.ResponseDto;
import com.bookstoe_project.dto.ShippingAddressDto;
import com.bookstoe_project.entity.ShippingAddress;
import com.bookstoe_project.service.ShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
@CrossOrigin(origins = "http://localhost:4200")
public class ShippingAddressController {

    @Autowired
    ShippingAddressService shippingAddressService;

    @PostMapping("/saveaddress")
    ResponseEntity<ResponseDto> createShippingAddress(@RequestParam String token, @RequestBody ShippingAddressDto shippingAddressDto){
        ShippingAddress shippingAddress = shippingAddressService.saveShippingAddress(shippingAddressDto, token);
        ResponseDto responseDto = new ResponseDto("Address saved successfully", shippingAddress);
        return new ResponseEntity<>(responseDto ,HttpStatus.OK);
    }
}

package com.bookstoe_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShippingAddressDto {
    private long pinCode;
    private String locality;
    private String address;
    private String city;
    private String landmark;
    private String addressType;
}

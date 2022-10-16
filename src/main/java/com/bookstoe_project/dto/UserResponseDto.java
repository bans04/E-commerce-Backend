package com.bookstoe_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
//    private long userId;
    private String fullName;
//    private String email;
//    private String password;
    private long mobileNo;
    private String token;
}

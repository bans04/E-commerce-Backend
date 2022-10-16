package com.bookstoe_project.exception;

import com.bookstoe_project.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class BookstoreExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        List errorList = ex.getBindingResult().getAllErrors().stream().map(objectError ->
                objectError.getDefaultMessage()).collect(Collectors.toList());
        ResponseDto responseDto = new ResponseDto("Exception occurs while processing rest request", errorList);
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    ResponseEntity<ResponseDto> userNotFoundException(UserNotFoundException userNotFoundException){
        ResponseDto responseDto = new ResponseDto("Exception occurs while processing rest request", userNotFoundException.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    ResponseEntity<ResponseDto> invalidPassword(InvalidPassword invalidPassword){
        ResponseDto responseDto = new ResponseDto("Exception occurs while processing rest request", invalidPassword.getMessage());
        return new ResponseEntity<>(responseDto,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NoAccountFound.class)
    public ResponseEntity<ResponseDto> noAccountFound(NoAccountFound ex) {
        ResponseDto responseDto = new ResponseDto("Exception occurs while processing rest request", ex.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BookNotFound.class)
    public ResponseEntity<ResponseDto> bookNotFound(BookNotFound ex) {
        ResponseDto responseDto = new ResponseDto("Exception occurs while processing rest request", ex.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }
}

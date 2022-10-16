package com.bookstoe_project.controller;

import com.bookstoe_project.dto.BookDto;
import com.bookstoe_project.dto.ResponseDto;
import com.bookstoe_project.entity.Book;
import com.bookstoe_project.repository.BookRepository;
import com.bookstoe_project.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    BookRepository bookRepository;

    @RequestMapping("/add")
    ResponseEntity<ResponseDto> addBook(@RequestBody BookDto bookDto){
        BookDto bookDto1 = bookService.addBook(bookDto);
        ResponseDto responseDto = new ResponseDto("Book Added successfully", bookDto1);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/books")
    public ResponseEntity<ResponseDto> getBooks() {
//        List<BookDto> bookDataDto = bookService.getBookData();
        List<Book> book = bookService.getBookData();
        ResponseDto responseDto = new ResponseDto("Your book data retrieve successfully ", book);
        return  new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/filterlowtohigh")
    public ResponseEntity<ResponseDto> filterBooks() {
        List<Book> book = bookService.filterBookByLowToHighPrice();
        ResponseDto responseDto = new ResponseDto("Your book data retrieve successfully ", book);
        return  new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/filterhightolow")
    public ResponseEntity<ResponseDto> filterBooksHighToLow() {
        List<Book> book = bookService.filterBookByLowToHighPrice();
        ResponseDto responseDto = new ResponseDto("Your book data retrieve successfully ", book);
        return  new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/filterbook")
    public ResponseEntity<ResponseDto> filterBook(@RequestParam String bookName) {
        Book book = bookService.filterByBookName(bookName);
        ResponseDto responseDto = new ResponseDto("Your book data retrieve successfully ", book);
        return  new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}

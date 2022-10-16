package com.bookstoe_project.service;

import com.bookstoe_project.dto.BookDto;
import com.bookstoe_project.entity.Book;
import com.bookstoe_project.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    ModelMapper modelMapper;

    public BookDto addBook(BookDto bookDto){
        Book book = modelMapper.map(bookDto, Book.class);
        bookRepository.save(book);
        return bookDto;
    }

    public List<Book> getBookData(){
        List<Book> booksData = bookRepository.findAll();
        return booksData;
    }

    public List<Book> filterBookByLowToHighPrice(){
        return bookRepository.filterBookByLowToHigh();
    }

    public List<Book> filterBookByHighToLowPrice(){
        return bookRepository.filterBookByHighToLow();
    }

    public Book filterByBookName(String bookName){
        return bookRepository.findByBookName(bookName);
    }
}

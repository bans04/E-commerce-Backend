package com.bookstoe_project.repository;

import com.bookstoe_project.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByBookName(String bookName);

    @Query("select b from Book b order by b.price")
    List<Book> filterBookByLowToHigh();

    @Query("select b from Book b order by b.price asc ")
    List<Book> filterBookByHighToLow();

}

package com.bookstoe_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    @JsonIgnore
    private UserData userData;

    @OneToMany
    private Set<Book> bookSet = new HashSet<>();


//    @ManyToOne
//    @JoinColumn(name = "bookId")
//    private Book book;

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "bookId")
//    @JsonIgnore
//    private List<Book> books;

    private Double totalPrice;
    private Long totalBooks;
}

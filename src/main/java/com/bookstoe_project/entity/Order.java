package com.bookstoe_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;
    java.time.LocalDate LocalDate;
    private double orderPrice;
    private long quantity;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shippingAddressId")
    @JsonIgnore
    private ShippingAddress shippingAddress;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    @JsonIgnore
    private UserData userData;

    @OneToMany
    @JoinColumn(name = "bookIdOrd")
    private Set<Book> orderBookList = new HashSet<>();
//    private Set<Book> orderBookList;

    boolean cancel;
}

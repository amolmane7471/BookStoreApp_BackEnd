package com.bridgelabz.bookstoreapp.entity;

import com.bridgelabz.bookstoreapp.dto.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orderData")
public class OrderData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate orderDate;
    private long quantity;
    private String address;
    @JoinColumn(name = "user_id")
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    @JoinColumn(name = "book_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private BookData book;
    private boolean cancel = false;
    private int orderPrice;


    public OrderData(User user, BookData book,int orderPrice, OrderDTO orderDto) {
        this.user = user;
        this.book = book;
        this.orderDate = LocalDate.now();
        this.quantity = orderDto.getQuantity();
        this.address = orderDto.getAddress();
        this.orderPrice = orderPrice;
    }
}

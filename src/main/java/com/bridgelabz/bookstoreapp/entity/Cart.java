package com.bridgelabz.bookstoreapp.entity;

import com.bridgelabz.bookstoreapp.dto.CartDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private BookData book;
    @Column(name = "quantity")
    private long quantity;
    @Column(name = "total_price")
    private int totalPrice;


    public Cart(User user , BookData book,int cartPrice, CartDto cartDto) {
        this.user = user;
        this.book = book;
        this.quantity = cartDto.getQuantity();
        this.totalPrice = cartPrice;
    }


}

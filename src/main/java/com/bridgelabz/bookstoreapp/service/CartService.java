package com.bridgelabz.bookstoreapp.service;
import com.bridgelabz.bookstoreapp.dto.CartDto;
import com.bridgelabz.bookstoreapp.entity.BookData;
import com.bridgelabz.bookstoreapp.entity.Cart;
import com.bridgelabz.bookstoreapp.entity.User;
import com.bridgelabz.bookstoreapp.repository.BookRepository;
import com.bridgelabz.bookstoreapp.repository.CartRepo;
import com.bridgelabz.bookstoreapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService{
    @Autowired
    CartRepo cartRepo;
    @Autowired
    UserRepository userRepo;
    @Autowired
    BookRepository bookRepo;

    @Autowired
    IUserService userService;

    @Autowired
    IBookService bookService;
    @Override
    public Cart addToCart(long userId, CartDto cartDto) {
        User user = userRepo.findById(userId).orElse(null);
        BookData book = bookService.getBookById(cartDto.getBookId());
        if(user != null && book != null){
            int cartPrice = (int) (book.getPrice() * cartDto.getQuantity());
            Cart cart = new Cart(user,book,cartPrice,cartDto);
            return cartRepo.save(cart);
        }
        return null;
    }

    @Override
    public String deleteById(int cartid) {
        Optional<Cart> cart = cartRepo.findById(cartid);
        if(cart != null) {
            cartRepo.deleteById(cartid);
        }
        return "cart is empty";
    }

    @Override
    public String changeCartQty(long userId, int cartId, CartDto cartDto) {
        User user = userRepo.findById(userId).orElse(null);

        Cart cart = cartRepo.findById(cartId).orElse(null);

        if(cart != null && user != null){
            BookData book = bookRepo.findById(cartDto.getBookId()).orElse(null);
            if(book != null){

                cart.setQuantity(cartDto.getQuantity());
                cart.setTotalPrice((int) (book.getPrice() * cartDto.getQuantity()));

                return "Updated with quantity : "+cartRepo.save(cart);
            }
        }
        return null;
    }

    @Override
    public List<Cart> findAll() {
        List<Cart> cartList = cartRepo.findAll();
        return cartList;
    }

    @Override
    public List<Cart> getCartDetailsByUserId(long userId) {
        List<Cart> userCartList = cartRepo.getCartDetailsByUserId(userId);
        if(userCartList.isEmpty()){
            return null;
        }else
            return userCartList;
    }
}

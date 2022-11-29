package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.CartDto;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.entity.Cart;
import com.bridgelabz.bookstoreapp.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ICartService cartService;

    @PostMapping("/addToCart/{userId}")
    public ResponseEntity<ResponseDTO> addToCart(@PathVariable long userId, @RequestBody CartDto cartDto){
        Cart cart = cartService.addToCart(userId,cartDto);
        ResponseDTO responseDto = new ResponseDTO("Cart Added  Successfully!!",cart);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // Ability to delete cart details by id
    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable int cartId) {
        cartService.deleteById(cartId);
        ResponseDTO reponseDTO = new ResponseDTO("cart Data deleted successfully!!", "deleted id " + cartId);
        return new ResponseEntity(reponseDTO, HttpStatus.ACCEPTED);
    }

    //Ability to update  quantity
    @PutMapping("/updatequantity/{userId}/{cartId}")
    public ResponseEntity<ResponseDTO> changeBookQuantity(@PathVariable long userId, @PathVariable int cartId, @RequestBody CartDto cartDto) {
        String cart = cartService.changeCartQty(userId,cartId,cartDto);
        ResponseDTO responseDTO = new ResponseDTO("quantity of Cart Updated successfully", cart);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // get all cart data
    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> findAllDetails() {
        List<Cart> cartList = cartService.findAll();
        ResponseDTO responseDTO = new ResponseDTO("All CartList Retrieved Successfully", cartList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    //Get Cart Data by UserId
    @GetMapping("/UserCart/{userId}")
    public ResponseEntity<ResponseDTO> getCartDetailsById(@PathVariable long userId){
        List<Cart> userCartDetails = cartService.getCartDetailsByUserId(userId);
        ResponseDTO responseDTO = new ResponseDTO("Cart Details of Given ID are Retrieved Successfully", userCartDetails);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}

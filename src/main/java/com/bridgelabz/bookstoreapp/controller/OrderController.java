package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.OrderDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.entity.OrderData;
import com.bridgelabz.bookstoreapp.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    IOrderService iOrderService;

    @PostMapping("/placeOrder/{userId}")
    public ResponseEntity<ResponseDTO> placeOrder(@PathVariable long userId, @RequestBody OrderDTO orderDto) {
        OrderData order = iOrderService.placeOrder(userId, orderDto);
        ResponseDTO response = new ResponseDTO("Order Placed", order.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/userOrders/{userId}")
    public ResponseEntity<ResponseDTO> getUserOrders(@PathVariable("userId") int userId){
        List<OrderData> userOrders = iOrderService.userOrders(userId);
        ResponseDTO response = new ResponseDTO("Orders of user ", userOrders);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/getAllOrder")
    public ResponseEntity<ResponseDTO> getAllOrders(){
        List<OrderData> allOrderData = iOrderService.getAllOrders();
        ResponseDTO responseDTO = new ResponseDTO("Order data:", allOrderData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/cancelOrder/{userId}/{orderId}")
    public ResponseEntity<ResponseDTO> cancelOrder(@PathVariable int orderId, @PathVariable long userId) {
        String order = iOrderService.cancelOrder(orderId, userId);
        ResponseDTO response = new ResponseDTO("Order Cancelled ", order);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

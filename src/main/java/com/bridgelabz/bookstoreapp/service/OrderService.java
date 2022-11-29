package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.OrderDTO;
import com.bridgelabz.bookstoreapp.entity.BookData;
import com.bridgelabz.bookstoreapp.entity.OrderData;
import com.bridgelabz.bookstoreapp.entity.User;
import com.bridgelabz.bookstoreapp.repository.BookRepository;
import com.bridgelabz.bookstoreapp.repository.CartRepo;
import com.bridgelabz.bookstoreapp.repository.OrderRepository;
import com.bridgelabz.bookstoreapp.repository.UserRepository;
import com.bridgelabz.bookstoreapp.util.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepo cartRepository;
    @Autowired
    private EmailSenderService mailService;
    @Override
    public OrderData placeOrder(long userId, OrderDTO orderDto) {
        User user = userRepo.findById(userId).orElse(null);
        BookData book = bookRepository.findById(orderDto.getBookId()).orElse(null);
        if (user != null) {
            int orderPrice = (int) (book.getPrice() * orderDto.getQuantity());
            book.setQuantity(book.getQuantity()-orderDto.getQuantity());

            OrderData order = new OrderData(user,book,orderPrice,orderDto);
            orderRepository.save(order);
            cartRepository.deleteAll();
            mailService.sendEmailToUser(user.getEmail(),"Order Placed",
                    "Book Name :"+order.getBook().getBookName()+
                            "\n" +"Book Description :"+order.getBook().getBookDescription()+
                            "\n" +"Book Price :"+order.getBook().getPrice()+
                            "\n" +"Order Quantity :"+orderDto.getQuantity() +
                            "\n" +"Order Price :"+orderPrice);
            return order;
        }
        return null;
    }

    @Override
    public String cancelOrder(int orderId, long userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user != null) {
            OrderData order = orderRepository.findById((long) orderId).orElse(null);
            if (order != null) {
                order.setCancel(true);
                BookData book = bookRepository.findById(order.getBook().getId()).orElse(null);
                book.setQuantity(book.getQuantity() + order.getQuantity());
                mailService.sendEmailToUser(user.getEmail(), "For Cancel Order","Order Id " + orderId+"\n"+order);
                orderRepository.save(order);
                return "Order Cancelled";
            }
        }
        return "User Not Found !!";
    }

    @Override
    public List<OrderData> userOrders(long userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user != null) {
            List<OrderData> order = orderRepository.findAllByUserId(userId);
            return order;
        }
        return null;
    }

    @Override
    public List<OrderData> getAllOrders() {
        List<OrderData> allOrderData = orderRepository.findAll();
        return allOrderData;
    }

}

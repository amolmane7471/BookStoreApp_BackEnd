package com.bridgelabz.bookstoreapp.controller;


import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.dto.UserDTO;
import com.bridgelabz.bookstoreapp.dto.LoginUser;
import com.bridgelabz.bookstoreapp.entity.User;
import com.bridgelabz.bookstoreapp.service.UserService;
import com.bridgelabz.bookstoreapp.util.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private UserService userService;

    @RequestMapping("/welcome")
    public String welcome(){
        String text = "Welcome To Book Store !!! ";
        return text;
    }

    //register a user
    @PostMapping("/register")
    public ResponseEntity<User> addUser(@RequestBody UserDTO userDTO){
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = userService.addUser(userDTO);
        return ResponseEntity.ok(user);
    }

    //fetch all users from db
    @GetMapping("/getUsers")
    public ResponseEntity<ResponseDTO> getAllUsers() {
        List<User> users = userService.getAllUsers();
        ResponseDTO responseDTO = new ResponseDTO("Got all users Successfully", users);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    //retrieve user by their id
    @GetMapping("/getUser/{id}")
    public ResponseEntity<ResponseDTO> getUserById(@PathVariable Long id){
        User userById = userService.getUserById(id);
        ResponseDTO responseDTO = new ResponseDTO("Got user whose id is " + userById.getId(), userById);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //update user by using id
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<ResponseDTO> updateUser(@PathVariable Long id ,@RequestBody UserDTO userDTO){
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User updatedUser = this.userService.updateUser(id, userDTO);
        ResponseDTO responseDTO = new ResponseDTO("User with id "+id+" Updated Successfully",updatedUser);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //delete user by using id
    @DeleteMapping("deleteUser/{id}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable Long id ){
        User deletedUser = this.userService.deleteUser(id);
        ResponseDTO responseDTO = new ResponseDTO("User with id "+id+" Deleted Successfully", deletedUser);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/resetPassword")
    public ResponseEntity<ResponseDTO> changePassword(@RequestBody LoginUser loginDTO) {
        String response = userService.resetPassword(loginDTO);
        ResponseDTO responseDTO = new ResponseDTO("Password successfully changed", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/forgotpassword")
    public ResponseEntity<ResponseDTO> forgotPasswordRequest(@RequestBody LoginUser loginUser) {
        String otp = userService.forgotPasswordRequest(loginUser.email);
        ResponseDTO respDTO = new ResponseDTO("Otp sent Succesfully", otp);
        return new ResponseEntity(respDTO, HttpStatus.OK);
    }

}

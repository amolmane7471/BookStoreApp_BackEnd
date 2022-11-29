package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.UserDTO;
import com.bridgelabz.bookstoreapp.dto.LoginUser;
import com.bridgelabz.bookstoreapp.entity.User;

import java.util.List;

public interface IUserService {
     User addUser(UserDTO user);
     List<User> getAllUsers();
    User getUserById(Long id);

    User updateUser(Long id, UserDTO userDTO);

    User deleteUser(Long id);

    String resetPassword(LoginUser loginDTO);

    String forgotPasswordRequest(String email);

}

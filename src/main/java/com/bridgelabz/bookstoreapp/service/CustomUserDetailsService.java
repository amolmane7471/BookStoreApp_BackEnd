package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.entity.User;
import com.bridgelabz.bookstoreapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByemail(email);

        if(user == null){
            throw new UsernameNotFoundException("User not Found!!");
        }
        else{
            return new CustomUserDetails(user);
        }

    }
}

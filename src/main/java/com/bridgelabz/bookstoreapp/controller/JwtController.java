package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.JwtResponse;
import com.bridgelabz.bookstoreapp.dto.LoginUser;
import com.bridgelabz.bookstoreapp.util.JwtUtil;
import com.bridgelabz.bookstoreapp.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @PostMapping("/login")
    public ResponseEntity<?> generateToken(@RequestBody LoginUser loginUser) throws Exception {
        System.out.println(loginUser);
        try {
            String username = loginUser.getEmail();
            String password = loginUser.getPassword();

            UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(username, password);
            this.authenticationManager.authenticate(user);


        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            System.out.println("User invalid");
            throw new Exception("Bad Credentials");
        }catch (BadCredentialsException e){
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        }

        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(loginUser.getEmail());
        String generatedToken = this.jwtUtil.generateToken(userDetails);
        System.out.println("JWT" + generatedToken);

        return ResponseEntity.ok(new JwtResponse(generatedToken));
    }
}

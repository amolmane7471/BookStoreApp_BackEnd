package com.bridgelabz.bookstoreapp.dto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class UserDTO {

    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "First name is Invalid")
    @NotEmpty(message = "First name cannot be null")
    private String firstName;

    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Last name is Invalid")
    @NotEmpty(message = "Last name cannot be null")
    private String lastName;

    @NotEmpty(message = "address cannot be null")
    private String address;

    @Pattern(regexp = "^[0-9a-zA-Z!,@#$&*().]{8,}$", message = "Password is Invalid")
    @NotEmpty(message = "Password cannot be null")
    private String password;

    @Pattern(regexp = "^[a-z]+[a-z0-9+_.-]*[@][a-z0-9]+[.][a-z]{2,4}[.]*([a-z]{2,3})*$", message = "email is invalid")
    @NotEmpty(message = "email should not be empty")
    private String email;

    private boolean enabled;
}

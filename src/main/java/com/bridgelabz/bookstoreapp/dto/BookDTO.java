package com.bridgelabz.bookstoreapp.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class BookDTO {
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Book name is Invalid")
    @NotEmpty(message = "Book name cannot be null")
    private String bookName;

    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Author name is Invalid")
    @NotEmpty(message = "Author name cannot be null")
    private String author;

    @NotEmpty(message = "Description cannot be null")
    private String bookDescription;

    @NotEmpty(message = "Book Image cannot be null")
    private String bookImg;

    @NotEmpty(message = "price cannot be null")
    private double price;

    @NotEmpty(message = "Quantity cannot be null")
    private long quantity;

}

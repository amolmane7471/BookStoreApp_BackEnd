package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.entity.BookData;

import java.util.List;

public interface IBookService {

     BookData addBook(BookDTO bookDTO);

     List<BookData> getAllBooks();

     BookData getBookById(Long id);

     BookData updateBookDetails(Long id, BookDTO bookDTO);

     BookData removeBookFromStocks(Long id);

    List<BookData> searchByName(String name);

    List<BookData> getBookByAscendingPrice();

    List<BookData> getBookByDescendingPrice();

    int getTotalBooksCount();
}

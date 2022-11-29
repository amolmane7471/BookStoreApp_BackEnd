package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.entity.BookData;
import com.bridgelabz.bookstoreapp.entity.User;
import com.bridgelabz.bookstoreapp.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private IBookService bookService;

    /**
     *@Purpose :adding books into the book stocks
     */
    @PostMapping("/addBook")
    public ResponseEntity<ResponseDTO> addBookToStock(@RequestBody BookDTO bookDTO){
        BookData bookData = bookService.addBook(bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Added Book successfully", bookData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    /**
     *@Purpose :To get All Books from the stock
     */
    @GetMapping("/getBooks")
    public ResponseEntity<ResponseDTO> getAllBooks(){
        List<BookData> bookData = bookService.getAllBooks();
        ResponseDTO responseDTO = new ResponseDTO("Get all books Successfully", bookData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /**
     *@Purpose : To get Book By book Id
     */
    @GetMapping("/getBook/{id}")
    public ResponseEntity<ResponseDTO> getBookById(@PathVariable Long id){
        BookData bookDataById = bookService.getBookById(id);
        ResponseDTO responseDTO = new ResponseDTO("Book with ID " +id+ "is: ", bookDataById);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     *@Purpose : To update Book By ID
     */
    @PutMapping("/updateBook/{id}")
    public ResponseEntity<ResponseDTO> updateBookDetails(@PathVariable Long id, @RequestBody BookDTO bookDTO){
        BookData bookData = bookService.updateBookDetails(id, bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Book with ID " +id+ "is updated successfully: ", bookData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    /**
     *@Purpose :To remove Book from the book stock
     */
    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<ResponseDTO> removeBookFromStocks(@PathVariable Long id){
        BookData bookData = bookService.removeBookFromStocks(id);
        ResponseDTO responseDTO = new ResponseDTO("Book with ID " +id+ "is deleted successfully: ", bookData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * @Purpose : To search book by name
     */
    @GetMapping("/searchByName/{name}")
    public ResponseEntity<ResponseDTO> searchByName(@PathVariable String name ) {
        List<BookData> bookDataDataList = bookService.searchByName(name);
        ResponseDTO respDTO = new ResponseDTO("Books are ....", bookDataDataList);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    /**
     * @Purpose : To show total Book Count in book store
     */
    @GetMapping("/totalBookCount")
    public ResponseEntity<ResponseDTO> getTotalBookCount() {
        int totalCount = bookService.getTotalBooksCount();
        return new ResponseEntity<>(new ResponseDTO("Total books are  : ", totalCount), HttpStatus.OK);
    }

    /**
     * @Purpose : To get Book By Ascending Price
     */
    @GetMapping("/getBookByAscendingPrice")
    public ResponseEntity<ResponseDTO> getBookByAscendingPrice() {
        List<BookData> bookDataDataList = bookService.getBookByAscendingPrice();
        ResponseDTO respDTO = new ResponseDTO("Books in ascending order...", bookDataDataList);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    /**
     * @Purpose : To get Book By Descending Price
     */
    @GetMapping("/getBookByDescendingPrice")
    public ResponseEntity<ResponseDTO> getBookByDescendingPrice() {
        List<BookData> bookDataDataList = bookService.getBookByDescendingPrice();
        ResponseDTO respDTO = new ResponseDTO("Books in descending order...", bookDataDataList);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }
}

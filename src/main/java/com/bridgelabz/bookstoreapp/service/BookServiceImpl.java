package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.entity.BookData;
import com.bridgelabz.bookstoreapp.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BookData addBook(BookDTO bookDTO) {
        BookData bookData = modelMapper.map(bookDTO, BookData.class);
        return bookRepository.save(bookData);
    }

    @Override
    public List<BookData> getAllBooks() {
        List<BookData> allBookData = bookRepository.findAll();
        return allBookData;
    }

    @Override
    public BookData getBookById(Long id) {
        BookData bookData = bookRepository.findById(id).get();
        return bookData;
    }

    @Override
    public BookData updateBookDetails(Long id, BookDTO bookDTO) {
        BookData bookDataById = this.getBookById(id);
        modelMapper.map(bookDTO, bookDataById);
        bookRepository.save(bookDataById);
        return bookDataById;
    }

    @Override
    public BookData removeBookFromStocks(Long id) {
        BookData bookData = this.getBookById(id);
        bookRepository.delete(bookData);
        return bookData;
    }

    @Override
    public int getTotalBooksCount() {
        return bookRepository.findAll().size();
    }

    @Override
    public List<BookData> searchByName(String name) {
        String name1 = name.toLowerCase();
        List<BookData> bookData = getAllBooks();
        List<BookData> collect = bookData.stream()
                .filter(bookDataData -> bookDataData.getBookName().toLowerCase().contains(name1))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<BookData> getBookByAscendingPrice() {
        List<BookData> bookDataDataList = bookRepository.findByOrderAsc();
        return bookDataDataList;
    }

    @Override
    public List<BookData> getBookByDescendingPrice() {
        List<BookData> bookDataDataList = bookRepository.findByOrderDesc();
        return bookDataDataList;
    }
}

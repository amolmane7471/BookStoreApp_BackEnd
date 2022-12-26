package com.bridgelabz.bookstoreapp.repository;
import com.bridgelabz.bookstoreapp.model.BookData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface BookRepository extends JpaRepository<BookData, Integer> {
    @Query("from BookData where bookId=:bookId")
    BookData getBookById(int bookId);
}

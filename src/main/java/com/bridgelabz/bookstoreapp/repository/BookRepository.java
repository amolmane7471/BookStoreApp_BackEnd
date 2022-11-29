package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.entity.BookData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<BookData, Long> {
    @Query("FROM BookData ORDER BY price ASC ")
    List<BookData> findByOrderAsc();

    @Query("FROM BookData ORDER BY price DESC ")
    List<BookData> findByOrderDesc();
}

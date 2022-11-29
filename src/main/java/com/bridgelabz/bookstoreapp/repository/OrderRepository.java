package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.entity.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderData, Integer> {
    @Query(value = "select * from order_data where user_id = :userId", nativeQuery = true)
    List<OrderData> findAllByUserId(long userId);
}
package com.example.demo.repository;

import com.example.demo.dto.request.transaction.TransactionReq;
import com.example.demo.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    @Query("SELECT t FROM Transaction t WHERE t.id = ?1")
    Transaction getById(int id);

    @Query("SELECT t FROM Transaction t WHERE t.id = ?1 AND t.sp_orderId = ?2")
    Transaction getTransaction(int id,int orderId);
}

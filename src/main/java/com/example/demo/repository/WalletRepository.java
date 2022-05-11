package com.example.demo.repository;

import com.example.demo.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface WalletRepository extends JpaRepository<Wallet, Integer> {
    @Query("SELECT w FROM Wallet w WHERE w.phoneNumber = ?1 AND w.password = ?2")
    Wallet walletCheck(String phoneNumber,String password);
    @Query("SELECT w FROM Wallet w WHERE w.phoneNumber = ?1 AND w.balance >= ?2")
    Wallet balanceCheck(String phoneNumber,long balance);
    @Modifying
    @Query("UPDATE Wallet w SET w.balance = ?2 WHERE w.id = ?1")
    void makeTransaction(int id,long balance);
}

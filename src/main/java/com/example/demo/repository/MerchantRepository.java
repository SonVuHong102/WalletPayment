package com.example.demo.repository;

import com.example.demo.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MerchantRepository extends JpaRepository<Merchant, Integer> {
    @Query("SELECT m FROM Merchant m WHERE m.id = ?1")
    Merchant getMerchant(int id);
    @Query("SELECT m FROM Merchant m WHERE m.id = ?1 AND m.secureCode = ?2")
    Merchant verifyMerchant(int id, String secureCode);
    @Modifying
    @Query("UPDATE Merchant m SET m.balance = ?2 WHERE m.id = ?1")
    void makeTransaction(int id,long balance);
}

package com.example.demo.repository;

import com.example.demo.model.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OTPRepository extends JpaRepository<OTP, Integer> {
    @Query("SELECT t FROM OTP t WHERE t.id = ?1 AND t.otpCode = ?2")
    OTP checkOTP(int id,String code);
}

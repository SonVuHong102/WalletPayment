package com.example.demo.repository;

import com.example.demo.model.OTP;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OTPRepository extends JpaRepository<OTP, Integer> {
}

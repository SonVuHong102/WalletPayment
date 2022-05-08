package com.example.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "otpTbl")
@Data
public class OTP extends BaseEntity {
    @Column(nullable = false)
    String transactionId;
    @Column(nullable = false)
    String walletId;
    @Column(nullable = false)
    String otpCode;
    long expiredTime = 600;
}

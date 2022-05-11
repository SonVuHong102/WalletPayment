package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "otptbl")
@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class OTP extends BaseEntity {
    @Column(nullable = false)
    int transactionId;
    @Column(nullable = false)
    int walletId;
    @Column(nullable = false)
    String otpCode;
    long expiredTime = 600;
}

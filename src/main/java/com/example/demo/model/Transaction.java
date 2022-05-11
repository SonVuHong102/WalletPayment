package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "transactiontbl")
@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Transaction extends BaseEntity{
    @Column(nullable = false)
    int merchantId;
    @Column(nullable = false)
    Long amount;
    int walletId;
    @Column(nullable = false)
    int orderId;
    String orderDescription = "";
    @Column(nullable = false)
    String returnUrl;
    @Column(nullable = false)
    String status;
    String errorCode;
}

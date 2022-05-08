package com.example.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "transactionTbl")
@Data
public class Transaction extends BaseEntity{
    @Column(nullable = false)
    String merchantId;
    @Column(nullable = false)
    Long amount;
    String walletId;
    @Column(nullable = false)
    String orderId;
    String orderDescription = "";
    @Column(nullable = false)
    String returnUrl;
    @Column(nullable = false)
    String status;
    @Column(nullable = false)
    String errorCode;
}

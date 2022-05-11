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
    int sp_merchantId;
    @Column(nullable = false)
    Long sp_amount;
    int sp_walletId;
    @Column(nullable = false)
    int sp_orderId;
    String sp_orderDescription = "";
    @Column(nullable = false)
    String sp_returnUrl;
    @Column(nullable = false)
    String status;
    String errorCode;
}

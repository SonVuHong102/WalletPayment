package com.example.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "walletTbl")
@Data
public class Wallet extends BaseEntity {
    @Column(nullable = false)
    String owner;
    @Column(nullable = false)
    String phoneNumber;
    @Column(nullable = false)
    String password;
    @Column(nullable = false)
    String email;
    @Column(nullable = false)
    Long balance;
}

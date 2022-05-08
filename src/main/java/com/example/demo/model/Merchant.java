package com.example.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "merchantTbl")
@Data
public class Merchant extends BaseEntity {
    @Column(nullable = false)
    int merchantId;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String phoneNumber;
    @Column(nullable = false)
    String secureCode;
    @Column(nullable = false)
    Long balance;
}

package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "merchanttbl")
@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Merchant extends BaseEntity {
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String phoneNumber;
    @Column(nullable = false)
    String secureCode;
    @Column(nullable = false)
    Long balance;
}

package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "wallettbl")
@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
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

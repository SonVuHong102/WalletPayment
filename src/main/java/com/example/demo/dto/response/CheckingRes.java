package com.example.demo.dto.response;

import lombok.Data;

@Data
public class CheckingRes implements IResponseData {
    Integer sp_merchantId;
    long sp_amount;
    Integer sp_orderId;
    String sp_secureHash;
    String message;
    String errorCode;
}

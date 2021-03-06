package com.example.demo.dto.request.payment;

import com.example.demo.dto.request.IRequestData;
import lombok.Data;

@Data
public class PaymentReq implements IRequestData {
    int merchantId;
    long amount;
    int orderId;
    String orderDescription;
    String phoneNumber;
    String password;
    String returnUrl;
    String secureCode;
    int transactionId;
}

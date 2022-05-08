package com.example.demo.dto.request.payment;

import com.example.demo.dto.request.IRequestData;

public class PaymentReq implements IRequestData {
    String merchantId;
    long amount;
    String orderId;
    String orderDescription;
    String returnUrl;
    String secureHash;
}

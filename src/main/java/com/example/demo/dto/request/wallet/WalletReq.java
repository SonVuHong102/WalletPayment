package com.example.demo.dto.request.wallet;

import com.example.demo.dto.request.IRequestData;

public class WalletReq implements IRequestData {
    String merchantId;
    long amount;
    String orderId;
    String orderDescription;
    String returnUrl;
    String secureHash;
}

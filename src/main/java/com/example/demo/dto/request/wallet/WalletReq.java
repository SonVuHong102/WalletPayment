package com.example.demo.dto.request.wallet;

import com.example.demo.dto.request.IRequestData;
import com.example.demo.model.Wallet;
import lombok.Data;

@Data
public class WalletReq extends Wallet implements IRequestData {
    int merchantId;
    long amount;
    int orderId;
    int transactionId;
    String orderDescription;
    String returnUrl;
}

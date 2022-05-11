package com.example.demo.service;

import com.example.demo.dto.request.payment.PaymentReq;
import com.example.demo.dto.request.transaction.TransactionReq;
import com.example.demo.dto.request.wallet.WalletReq;
import com.example.demo.dto.response.merchant.MerchantRes;
import com.example.demo.dto.response.payment.PaymentRes;
import com.example.demo.dto.response.transaction.TransactionRes;
import com.example.demo.dto.response.wallet.WalletRes;

public interface WalletService {
    PaymentRes payment(PaymentReq paymentReq);
    MerchantRes merchantVerify(PaymentReq paymentReq);
    WalletRes walletCheck(WalletReq walletReq);
    WalletRes sendOTP(WalletReq walletReq);
    WalletRes otpAuthenticate(WalletReq walletReq);
    WalletRes balanceCheck(WalletReq walletReq);
    TransactionRes makeTransaction(TransactionReq transactionReq);
    TransactionRes getTransaction(TransactionReq transactionReq);
    PaymentRes transactionchecking(PaymentReq paymentReq);
}

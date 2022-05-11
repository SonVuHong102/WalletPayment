package com.example.demo.service;

import com.example.demo.dto.request.BaseRequestData;
import com.example.demo.dto.request.merchant.MerchantReq;
import com.example.demo.dto.request.transaction.TransactionReq;
import com.example.demo.dto.request.wallet.WalletReq;
import com.example.demo.dto.response.merchant.MerchantRes;
import com.example.demo.dto.response.transaction.TransactionRes;
import com.example.demo.dto.response.wallet.WalletRes;
import com.example.demo.model.Wallet;
import com.example.demo.utils.exception.ApplicationException;

public interface WalletService {
    MerchantRes merchantVerify(MerchantReq merchantReq);
    WalletRes walletCheck(WalletReq walletReq);
    WalletRes sendOTP(WalletReq walletReq);
    WalletRes otpAuthenticate(WalletReq walletReq);
    WalletRes balanceCheck(WalletReq walletReq);
    TransactionRes makeTransaction(TransactionReq transactionReq);
    TransactionRes getTransaction(TransactionReq transactionReq);
}

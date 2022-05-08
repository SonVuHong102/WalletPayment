package com.example.demo.service;

import com.example.demo.dto.request.BaseRequestData;
import com.example.demo.dto.response.wallet.WalletRes;
import com.example.demo.model.Wallet;
import com.example.demo.utils.exception.ApplicationException;

public interface WalletService {
    WalletRes merchantVerify(BaseRequestData baseRequestData) throws ApplicationException;
}

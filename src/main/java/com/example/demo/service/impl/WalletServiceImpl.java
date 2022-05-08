package com.example.demo.service.impl;

import com.example.demo.dto.request.BaseRequestData;
import com.example.demo.dto.response.wallet.WalletRes;
import com.example.demo.repository.MerchantRepository;
import com.example.demo.service.WalletService;
import com.example.demo.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    MerchantRepository merchantRepository;

    @Override
    public WalletRes merchantVerify(BaseRequestData baseRequestData) throws ApplicationException {
        return null;
    }
}

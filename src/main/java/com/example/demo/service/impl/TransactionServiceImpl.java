package com.example.demo.service.impl;

import com.example.demo.dto.request.transaction.TransactionReq;
import com.example.demo.dto.response.transaction.TransactionRes;
import com.example.demo.model.Merchant;
import com.example.demo.model.Transaction;
import com.example.demo.model.Wallet;
import com.example.demo.repository.MerchantRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.WalletRepository;
import com.example.demo.service.TransactionService;
import com.example.demo.utils.Constants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    MerchantRepository merchantRepository;

    @Autowired
    WalletRepository walletRepository;

    @Override
    public TransactionRes getTransaction(TransactionReq transactionReq) {
        TransactionRes transactionRes = new TransactionRes();
        Transaction transaction = transactionRepository.getById(transactionReq.getId());
        transactionRes.setTransaction(transaction);
        return transactionRes;
    }

    @Override
    public TransactionRes transactionSuccess(TransactionReq transactionReq) {
        TransactionRes transactionRes = new TransactionRes();
        Wallet wallet = walletRepository.getById(transactionReq.getSp_walletId());
        if(wallet.getBalance() < transactionReq.getSp_amount())
            return transactionRes;
        long walletAmount = wallet.getBalance() - transactionReq.getSp_amount();
        walletRepository.makeTransaction(wallet.getId(),walletAmount);
        Merchant merchant = merchantRepository.getMerchant(transactionReq.getSp_merchantId());
        long merchantAmount = merchant.getBalance() + transactionReq.getSp_amount();
        merchantRepository.makeTransaction(merchant.getId(),merchantAmount);
        transactionReq.setStatus(Constants.STATUS_DONE);
        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(transactionReq,transaction);
        transactionRepository.save(transaction);
        transactionRes.setSuccess(true);
        return transactionRes;
    }
}

package com.example.demo.service.impl;

import com.example.demo.dto.request.merchant.MerchantReq;
import com.example.demo.dto.request.transaction.TransactionReq;
import com.example.demo.dto.request.wallet.WalletReq;
import com.example.demo.dto.response.merchant.MerchantRes;
import com.example.demo.dto.response.transaction.TransactionRes;
import com.example.demo.dto.response.wallet.WalletRes;
import com.example.demo.model.Merchant;
import com.example.demo.model.OTP;
import com.example.demo.model.Transaction;
import com.example.demo.model.Wallet;
import com.example.demo.repository.MerchantRepository;
import com.example.demo.repository.OTPRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.WalletRepository;
import com.example.demo.service.EmailService;
import com.example.demo.service.WalletService;
import com.example.demo.utils.Constants;
import com.example.demo.utils.OTPGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    MerchantRepository merchantRepository;

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    OTPRepository otpRepository;

    @Autowired
    EmailService emailService;

    @Override
    public MerchantRes merchantVerify(@RequestBody MerchantReq merchantReq) {
        MerchantRes merchantRes = new MerchantRes();
        Merchant merchant = merchantRepository.verifyMerchant(merchantReq.getMerchantId(),merchantReq.getSecureCode());
        merchantRes.setMerchant(merchant);
        return merchantRes;
    }

    @Override
    public WalletRes walletCheck(WalletReq walletReq) {
        WalletRes walletRes = new WalletRes();
        Wallet wallet = walletRepository.walletCheck(walletReq.getPhoneNumber(),walletReq.getPassword());
        walletRes.setWallet(wallet);
        return walletRes;
    }

    @Override
    public WalletRes sendOTP(WalletReq walletReq) {
        WalletRes walletRes = new WalletRes();
        String otpNum = OTPGenerator.genOTP();
        emailService.sendSimpleMessage(walletReq.getEmail(),walletReq.getOwner() + " OTP",otpNum);
        OTP opt = new OTP();
        return walletRes;
    }

    @Override
    public WalletRes otpAuthenticate(WalletReq walletReq){
        WalletRes walletRes = new WalletRes();
        return null;
    }

    @Override
    public WalletRes balanceCheck(WalletReq walletReq){
        WalletRes walletRes = new WalletRes();
        return null;
    }

    @Override
    public TransactionRes makeTransaction(TransactionReq transactionReq) {
        TransactionRes transactionRes = new TransactionRes();
        Wallet wallet = walletRepository.getById(transactionReq.getWalletId());
        long walletAmount = wallet.getBalance() - transactionReq.getAmount();
        walletRepository.makeTransaction(wallet.getId(),walletAmount);
        Merchant merchant = merchantRepository.getMerchant(transactionReq.getMerchantId());
        long merchantAmount = merchant.getBalance() + transactionReq.getAmount();
        merchantRepository.makeTransaction(transactionReq.getMerchantId(),merchantAmount);
        wallet = walletRepository.getById(wallet.getId());
        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(transactionReq,transaction);
        transaction.setStatus(Constants.STATUS_DONE);
        transaction.setWalletId(wallet.getId());
        transactionRepository.save(transaction);
        transactionRes.setTransaction(transaction);
        return transactionRes;
    }

    @Override
    public TransactionRes getTransaction(TransactionReq transactionReq) {
        TransactionRes transactionRes = new TransactionRes();
        Transaction transaction = transactionRepository.getById(transactionReq.getId());
        transactionRes.setTransaction(transaction);
        return transactionRes;
    }
}

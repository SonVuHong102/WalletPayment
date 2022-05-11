package com.example.demo.service.impl;

import com.example.demo.dto.request.merchant.MerchantReq;
import com.example.demo.dto.request.payment.PaymentReq;
import com.example.demo.dto.request.transaction.TransactionReq;
import com.example.demo.dto.request.wallet.WalletReq;
import com.example.demo.dto.response.merchant.MerchantRes;
import com.example.demo.dto.response.payment.PaymentRes;
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
import com.example.demo.utils.HashTool;
import com.example.demo.utils.OTPGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
    public PaymentRes payment(PaymentReq paymentReq) {
        PaymentRes paymentRes = new PaymentRes();
        // Xac minh xong
        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(paymentReq,transaction);
        return null;

    }

    @Override
    public MerchantRes merchantVerify(@RequestBody PaymentReq paymentReq) {
        MerchantRes merchantRes = new MerchantRes();
        Merchant merchant = merchantRepository.getMerchant(paymentReq.getSp_merchantId());
        if(merchant != null) {
//            if(paymentReq.getSp_secureHash().compareTo(HashTool.sha256hash(merchant.getSecureCode()))!=0)
//                return merchantRes;
            merchantRes.setMerchant(merchant);
            Transaction transaction = new Transaction();
            BeanUtils.copyProperties(paymentReq,transaction);
            transaction.setStatus(Constants.STATUS_PENDING);
            transaction = transactionRepository.save(transaction);
            merchantRes.setTransactionId(transaction.getId());
        }
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
        OTP otp = new OTP();
        otp.setOtpCode(otpNum);
        otp.setWalletId(walletReq.getId());
        otp.setTransactionId(walletReq.getTransactionId());
        otp = otpRepository.save(otp);
        walletRes.setOtpId(otp.getId());
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
//        Wallet wallet = walletRepository.getById(transactionReq.getWalletId());
//        long walletAmount = wallet.getBalance() - transactionReq.getAmount();
//        walletRepository.makeTransaction(wallet.getId(),walletAmount);
//        Merchant merchant = merchantRepository.getMerchant(transactionReq.getMerchantId());
//        long merchantAmount = merchant.getBalance() + transactionReq.getAmount();
//        merchantRepository.makeTransaction(transactionReq.getMerchantId(),merchantAmount);
//        wallet = walletRepository.getById(wallet.getId());
//        Transaction transaction = new Transaction();
//        BeanUtils.copyProperties(transactionReq,transaction);
//        transaction.setStatus(Constants.STATUS_DONE);
//        transaction.setWalletId(wallet.getId());
//        transactionRepository.save(transaction);
//        transactionRes.setTransaction(transaction);
        return transactionRes;
    }

    @Override
    public TransactionRes getTransaction(TransactionReq transactionReq) {
        TransactionRes transactionRes = new TransactionRes();
        Transaction transaction = transactionRepository.getById(transactionReq.getId());
        transactionRes.setTransaction(transaction);
        return transactionRes;
    }

    @Override
    public PaymentRes transactionchecking(PaymentReq paymentReq) {
        PaymentRes paymentRes = new PaymentRes();
        Transaction transaction = transactionRepository.getTransaction(paymentReq.getSp_merchantId(),paymentReq.getSp_orderId());
        paymentRes.setTransaction(transaction);
        return paymentRes;
    }
}

package com.example.demo.controller;

import com.example.demo.dto.request.merchant.MerchantReq;
import com.example.demo.dto.request.transaction.TransactionReq;
import com.example.demo.dto.request.wallet.WalletReq;
import com.example.demo.dto.response.merchant.MerchantRes;
import com.example.demo.dto.response.transaction.TransactionRes;
import com.example.demo.dto.response.wallet.WalletRes;
import com.example.demo.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/wallet/")
public class WalletController extends BaseController {

    @Autowired
    WalletService walletService;

    @PostMapping(value = "merchantVerify")
    @ResponseBody
    public MerchantRes merchantVerify(@RequestBody MerchantReq merchantReq) {
        return walletService.merchantVerify(merchantReq);
    }

    @PostMapping(value = "walletCheck")
    @ResponseBody
    public WalletRes walletCheck(@RequestBody WalletReq walletReq) {
        return walletService.walletCheck(walletReq);
    }

    @PostMapping(value = "sendOTP")
    @ResponseBody
    public WalletRes sendOTP(@RequestBody WalletReq walletReq) {
        return walletService.sendOTP(walletReq);
    }

    @PostMapping(value = "otpAuthenticate")
    @ResponseBody
    public WalletRes otpAuthenticate(@RequestBody WalletReq walletReq) {
        return walletService.otpAuthenticate(walletReq);
    }

    @PostMapping(value = "balanceCheck")
    @ResponseBody
    public WalletRes balanceCheck(@RequestBody WalletReq walletReq) {
        return walletService.balanceCheck(walletReq);
    }

    @PostMapping(value = "makeTransaction")
    @ResponseBody
    public TransactionRes makeTransaction(@RequestBody TransactionReq transactionReq) {
        return walletService.makeTransaction(transactionReq);
    }

    @PostMapping(value = "getTransaction")
    @ResponseBody
    public TransactionRes getTransaction(@RequestBody TransactionReq transactionReq) {
        return walletService.getTransaction(transactionReq);
    }
}

package com.example.demo.controller;

import com.example.demo.dto.request.otp.OTPReq;
import com.example.demo.dto.request.payment.PaymentReq;
import com.example.demo.dto.request.transaction.TransactionReq;
import com.example.demo.dto.request.wallet.WalletReq;
import com.example.demo.dto.response.CheckingRes;
import com.example.demo.dto.response.merchant.MerchantRes;
import com.example.demo.dto.response.otp.OTPRes;
import com.example.demo.dto.response.payment.PaymentRes;
import com.example.demo.dto.response.transaction.TransactionRes;
import com.example.demo.dto.response.wallet.WalletRes;
import com.example.demo.form.InputForm;
import com.example.demo.form.InputOTP;
import com.example.demo.form.InputTMDT;
import com.example.demo.model.OTP;
import com.example.demo.service.TransactionService;
import com.example.demo.service.WalletService;
import com.example.demo.service.OTPService;
import com.example.demo.utils.exception.ApplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@CrossOrigin
@RequestMapping("")
public class WalletController extends BaseController {

    @Autowired
    WalletService walletService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    OTPService otpService;

    @GetMapping("/")
    public String input(Model model) {
        InputTMDT inputTMDT = new InputTMDT();
        model.addAttribute("inputTMDT",inputTMDT);
        return "tmdt";
    }

    @GetMapping("/shopooresult")
    public String shopooresult(Model model) {
        InputTMDT inputTMDT = new InputTMDT();
        model.addAttribute("inputTMDT",inputTMDT);
        return "shopooresult";
    }

    @GetMapping(value = "/pay")
    public String pay(Model model,
                                 HttpServletRequest request,
                                 @ModelAttribute("inputTMDT") InputTMDT inputTMDT) {
        PaymentReq paymentReq = new PaymentReq();
        paymentReq.setSp_merchantId(inputTMDT.getSp_merchantId());
        paymentReq.setSp_amount(inputTMDT.getSp_amount());
        paymentReq.setSp_orderDescription(inputTMDT.getSp_orderDescription());
        paymentReq.setSp_orderId(inputTMDT.getSp_orderId());
        paymentReq.setSp_returnUrl(inputTMDT.getSp_returnUrl());
        paymentReq.setSp_secureHash(inputTMDT.getSp_secureHash());
        MerchantRes merchantRes = walletService.merchantVerify(paymentReq);
        if(merchantRes.getMerchant() != null) {
            InputForm inputForm = new InputForm();
            model.addAttribute("inputForm", inputForm);
            request.getSession().setAttribute("transactionId",merchantRes.getTransactionId());
            request.getSession().setAttribute("returnUrl",paymentReq.getSp_returnUrl());
            request.getSession().setAttribute("autoreturnUrl","3;" + paymentReq.getSp_returnUrl());
            return "inputForm";
        }
        return "errorPage";
    }

    @PostMapping("/inputPayment")
    public String inputPayment(Model model,HttpServletRequest request,@ModelAttribute("inputForm") InputForm inputForm) {
        String phoneNumber = inputForm.getPhoneNumber();
        String password = inputForm.getPassword();
        int transactionId = (int) request.getSession().getAttribute("transactionId");
        WalletReq walletReq = new WalletReq();
        walletReq.setTransactionId(transactionId);
        walletReq.setPhoneNumber(phoneNumber);
        walletReq.setPassword(password);
        WalletRes walletRes = walletService.walletCheck(walletReq);
        if(walletRes.getWallet() == null) {
            return "errorPage";
        }
        request.getSession().setAttribute("walletId",walletRes.getWallet().getId());
        BeanUtils.copyProperties(walletRes.getWallet(),walletReq);
        walletRes = walletService.sendOTP(walletReq);
        if(walletRes.getOtpId()==-1) {
            return "errorPage";
        }
        TransactionReq transactionReq = new TransactionReq();
        transactionReq.setId(transactionId);
        TransactionRes transactionRes = transactionService.getTransaction(transactionReq);
        if(transactionRes.getTransaction() == null) {
            return "errorPage";
        }
        request.getSession().setAttribute("otpId",walletRes.getOtpId());
        InputOTP inputOTP = new InputOTP();
        model.addAttribute("inputOTP",inputOTP);
        model.addAttribute("transaction",transactionRes.getTransaction());
        return "otpAuth";
    }

    @PostMapping("/result")
    public String result(Model model,HttpServletRequest request,@ModelAttribute("inputOTP") InputOTP inputOTP) {
        String otpCode = inputOTP.getOtp();
        OTPReq otpReq = new OTPReq();
        otpReq.setId((Integer) request.getSession().getAttribute("otpId"));
        otpReq.setOtpCode(otpCode);
        OTPRes otpRes = otpService.checkOTP(otpReq);
        if(!otpRes.isValid()) {
            return "errorPage";
        }
        TransactionReq transactionReq = new TransactionReq();
        transactionReq.setId((Integer) request.getSession().getAttribute("transactionId"));
        TransactionRes transactionRes = transactionService.getTransaction(transactionReq);
        if(transactionRes.getTransaction() == null) {
            return "errorPage";
        }
        BeanUtils.copyProperties(transactionRes.getTransaction(),transactionReq);
        transactionReq.setSp_walletId((Integer) request.getSession().getAttribute("walletId"));
        transactionRes = transactionService.transactionSuccess(transactionReq);
        if(!transactionRes.isSuccess()) {
            return "errorPage";
        }
        return "result";
    }

    @PostMapping(value = "transactionchecking")
    @ResponseBody
    public CheckingRes transactionchecking(@ModelAttribute("inputTMDT") InputTMDT inputTMDT) {
        PaymentRes paymentRes = walletService.transactionchecking(inputTMDT.getSp_merchantId(),inputTMDT.getSp_amount(),inputTMDT.getSp_orderId(),inputTMDT.getSp_secureHash());
        CheckingRes checkingRes = new CheckingRes();
        checkingRes.setSp_merchantId(inputTMDT.getSp_merchantId());
        checkingRes.setSp_amount(inputTMDT.getSp_amount());
        checkingRes.setSp_orderId(inputTMDT.getSp_orderId());
        checkingRes.setSp_secureHash(inputTMDT.getSp_secureHash());
        if(paymentRes.getTransaction() == null) {
            checkingRes.setErrorCode("001");
            checkingRes.setMessage("Khong tim thay giao dich");
        } else {
            checkingRes.setErrorCode("000");
            checkingRes.setMessage("Giao dich thanh cong");
        }
        return checkingRes;
    }

}

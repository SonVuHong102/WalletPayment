package com.example.demo.service;

import com.example.demo.dto.request.payment.PaymentReq;
import com.example.demo.dto.response.payment.PaymentRes;
import com.example.demo.utils.exception.ApplicationException;

public interface PaymentService {
    PaymentRes payment(PaymentReq paymentReq) throws ApplicationException;
    PaymentRes getTransaction(PaymentReq paymentReq) throws ApplicationException;
}

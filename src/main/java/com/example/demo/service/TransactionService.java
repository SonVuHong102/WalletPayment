package com.example.demo.service;

import com.example.demo.dto.request.transaction.TransactionReq;
import com.example.demo.dto.response.transaction.TransactionRes;

public interface TransactionService {
    TransactionRes getTransaction(TransactionReq transactionReq);
    TransactionRes transactionSuccess(TransactionReq transactionReq);
}

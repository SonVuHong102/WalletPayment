package com.example.demo.dto.request.transaction;

import com.example.demo.dto.request.IRequestData;
import com.example.demo.model.Transaction;
import lombok.Data;

@Data
public class TransactionReq extends Transaction implements IRequestData {
}

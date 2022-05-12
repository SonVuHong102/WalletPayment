package com.example.demo.dto.response.payment;

import com.example.demo.dto.response.IResponseData;
import com.example.demo.model.Transaction;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PaymentRes implements IResponseData {
    Transaction transaction;
    String status;
}

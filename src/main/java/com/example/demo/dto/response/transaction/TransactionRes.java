package com.example.demo.dto.response.transaction;

import com.example.demo.dto.response.IResponseData;
import com.example.demo.model.Transaction;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Data
public class TransactionRes implements IResponseData {
    Transaction transaction;
    boolean success = false;
}

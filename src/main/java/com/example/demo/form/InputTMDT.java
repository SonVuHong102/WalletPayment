package com.example.demo.form;

import lombok.Data;

@Data
public class InputTMDT {
    int sp_merchantId;
    long sp_amount;
    int sp_orderId;
    String sp_orderDescription;
    String sp_returnUrl;
    String sp_secureHash;
}

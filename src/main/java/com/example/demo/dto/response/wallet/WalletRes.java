package com.example.demo.dto.response.wallet;

import com.example.demo.dto.response.IResponseData;
import com.example.demo.model.Wallet;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class WalletRes implements IResponseData {
    Wallet wallet;
    int otpId = -1;
}

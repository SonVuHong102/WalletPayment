package com.example.demo.dto.response.otp;

import com.example.demo.dto.response.IResponseData;
import lombok.Data;

@Data
public class OTPRes implements IResponseData {
    boolean isValid;
}

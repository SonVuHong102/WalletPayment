package com.example.demo.service;

import com.example.demo.dto.request.otp.OTPReq;
import com.example.demo.dto.response.otp.OTPRes;

public interface OTPService {
    OTPRes checkOTP(OTPReq otpReq);
}

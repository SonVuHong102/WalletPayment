package com.example.demo.service.impl;

import com.example.demo.dto.request.otp.OTPReq;
import com.example.demo.dto.response.otp.OTPRes;
import com.example.demo.model.OTP;
import com.example.demo.repository.OTPRepository;
import com.example.demo.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OTPServiceImpl implements OTPService {

    @Autowired
    OTPRepository otpRepository;

    @Override
    public OTPRes checkOTP(OTPReq otpReq) {
        OTPRes otpRes = new OTPRes();
        OTP otp = otpRepository.checkOTP(otpReq.getId(),otpReq.getOtpCode());
        otpRes.setValid(otp != null);
        return otpRes;
    }
}

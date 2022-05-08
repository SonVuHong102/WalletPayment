package com.example.demo.controller;

import com.example.demo.dto.response.BaseResponseData;
import com.example.demo.dto.response.IResponseData;
import com.example.demo.utils.MessageUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {
    protected  <T extends IResponseData> ResponseEntity success(T response) {
        BaseResponseData baseResponseData= new BaseResponseData();
        baseResponseData.setErrorCode("200");
        baseResponseData.setMessage(MessageUtils.getMessage("success"));
        baseResponseData.setWsResponse(response);
        return new ResponseEntity(baseResponseData, HttpStatus.OK);
    }
    protected  <T extends IResponseData> ResponseEntity error(String code, String message) {
        BaseResponseData baseResponseData= new BaseResponseData();
        baseResponseData.setErrorCode(code);
        baseResponseData.setMessage(message);
        return new ResponseEntity(baseResponseData, HttpStatus.OK);
    }

    protected ResponseEntity success() {
        BaseResponseData baseResponseData= new BaseResponseData();
        baseResponseData.setErrorCode("200");
        baseResponseData.setMessage(MessageUtils.getMessage("success"));
        return new ResponseEntity(baseResponseData, HttpStatus.OK);
    }
}

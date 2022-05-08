package com.example.demo.controller;

import com.example.demo.dto.request.BaseRequestData;
import com.example.demo.dto.response.wallet.WalletRes;
import com.example.demo.service.WalletService;
import com.example.demo.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/wallet/")
public class WalletController extends BaseController {

    @Autowired
    WalletService walletService;

    @PostMapping(value = "merchantVerify")
    @ResponseBody
    public ResponseEntity merchantVerify(@RequestBody BaseRequestData baseRequestData) {
        try {
            WalletRes response = walletService.merchantVerify(baseRequestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
}

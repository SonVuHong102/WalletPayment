package com.example.demo.controller;

import com.example.demo.dto.request.payment.PaymentReq;
import com.example.demo.dto.response.payment.PaymentRes;
import com.example.demo.service.PaymentService;
import com.example.demo.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@CrossOrigin
@RequestMapping("/payment/")
public class MainController extends BaseController {

    @Autowired
    PaymentService paymentService;

    @PostMapping(value = "payment")
    @ResponseBody
    public ResponseEntity payment(@RequestBody PaymentReq paymentReq) {
        try {
            PaymentRes response = paymentService.payment(paymentReq);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "getTransaction")
    @ResponseBody
    public ResponseEntity getTransaction(@RequestBody PaymentReq paymentReq) {
        try {
            PaymentRes response = paymentService.getTransaction(paymentReq);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

}

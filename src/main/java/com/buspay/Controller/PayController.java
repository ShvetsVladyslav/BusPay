package com.buspay.Controller;

import com.buspay.Entity.PayCallback;
import com.buspay.Entity.PayData;
import com.buspay.Service.PayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/payment")
public class PayController {
    private static final Logger logger = LoggerFactory.getLogger(PayController.class);
    @Autowired
    PayService payService;
    @PostMapping("/create")
    public PayCallback paymentCreate(@RequestBody PayData payData){
        PayCallback callback;
        boolean response = payService.paymentCreate(payData);
        if (response){
            callback = new PayCallback(response, "OK", payData.getId());
        }
        else {
            callback = new PayCallback(response, "Pay creation error", null);
        }
        return callback;
    }
    @GetMapping("/find")
    public PayData getPayment(@RequestParam(value = "id") String id){
        PayData payment = payService.getPayment(id);
        if (payment != null){
            return payment;
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found");
        }
    }
}

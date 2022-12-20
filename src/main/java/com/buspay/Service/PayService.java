package com.buspay.Service;

import com.buspay.Entity.PayData;
import com.buspay.Repository.PaysRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Service
public class PayService {
    private static final Logger logger = LoggerFactory.getLogger(PayService.class);
    @Autowired
    PaysRepository paysRepository;
    public boolean paymentCreate(PayData payment){
        try{
            paysRepository.save(payment);
            return true;
        }
        catch (Exception exception){
            return false;
        }
    }
    public PayData getPayment(String id){
        if (paysRepository.findById(id).isPresent()) {
            return paysRepository.findById(id).get();
        }
        else {
            return null;
        }
    }
}

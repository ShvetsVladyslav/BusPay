package com.buspay.Service;

import com.buspay.Entity.PayData;
import com.buspay.Repository.PaysRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PayService {
    private static final Logger logger = LoggerFactory.getLogger(PayService.class);
    @Autowired
    PaysRepository paysRepository;
    public boolean paymentCreate(PayData payment){
        Random randomizer = new Random();
        switch (randomizer.nextInt(6) + 1) {
            case 1, 2 -> payment.setState("NEW");
            case 3, 4, 5 -> payment.setState("DONE");
            case 6 -> payment.setState("FAIL");
        }
        try {
            paysRepository.save(payment);
            return true;
        } catch (Exception exception) {
            logger.error(exception.getMessage());
            return false;
        }
    }
    public PayData getPayment(String id){
        PayData response;
        if (paysRepository.findById(id).isPresent()) {
            response =  paysRepository.findById(id).get();
        }
        else {
            response = null;
        }
        return response;
    }
    public void paymentCheck() {
        List<PayData> payQ = paysRepository.findByState("NEW");
    }
}

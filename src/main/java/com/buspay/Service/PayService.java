package com.buspay.Service;

import com.buspay.Entity.PayData;
import com.buspay.Repository.PaysRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PayService {
    private static final Logger logger = LoggerFactory.getLogger(PayService.class);
    @Autowired
    PaysRepository paysRepository;
    public boolean paymentCreate(PayData payment){

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
            Random randomizer = new Random();
            switch (randomizer.nextInt(6) + 1) {
                case 1, 2 -> response.setState("NEW");
                case 3, 4, 5 -> response.setState("DONE");
                case 6 -> response.setState("FAIL");
            }
            paysRepository.save(response);
        }
        else {
            response = null;
        }
        return response;
    }
    @Scheduled(fixedDelay = 10000)
    public void paymentCheck() throws InterruptedException {
        logger.info("Scheduled Job Start");
        List<PayData> payQ = paysRepository.findByState("NEW");
        logger.info("Count of payment to check: " + payQ.size());
        for (PayData payment:payQ) {
            logger.info("Payment id: " + payment.getId() + "\n" + "Payment state before: " + payment.getState());
            this.getPayment(payment.getId());
            logger.info("Payment id: " + payment.getId() + "\n" + "Payment state after: " + paysRepository.findById(payment.getId()).get().getState());
        }
        Thread.sleep(5000);
    }
}

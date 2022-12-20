package com.buspay.Repository;

import com.buspay.Entity.PayData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PaysRepository extends MongoRepository<PayData, String> {
    List<PayData> findByState(String state);
}

package com.buspay.Repository;

import com.buspay.Entity.PayData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaysRepository extends MongoRepository<PayData, String> {
}

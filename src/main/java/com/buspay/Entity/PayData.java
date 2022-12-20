package com.buspay.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;
@Getter
@Setter
@Document(collation = "Pays")
public class PayData {
    @MongoId
    private String id;
    @Field(name = "fullName")
    private String fullName;
    @Field(name = "price")
    private float price;
}

package com.buspay.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;
@Getter
@Setter
@Document(collation = "Pays")
public class PayData {
    @MongoId
    @Indexed(unique = true)
    private String id;
    @Field(name = "fullName")
    private String fullName;
    @Field(name = "price")
    private float price;
    @Field(name = "state")
    private String state;
    public PayData() {
    }
    public PayData(String fullName, float price) {
        this.fullName = fullName;
        this.price = price;
    }
}

package com.buspay.Controller;

import com.buspay.Entity.PayCallback;
import com.buspay.Entity.PayData;
import com.buspay.Service.PayService;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
@ExtendWith(MockitoExtension.class)
@WebMvcTest(PayController.class)
class PayControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    PayController payController;

    @Test
    @Description("whenPaymentCreate_shouldCallbackData")
    void paymentCreate() throws Exception {
        when(payController.paymentCreate(any(PayData.class))).thenReturn(new PayCallback(true,"OK", "Test"));
        PayData reqBody = new PayData();
        reqBody.setFullName("Test");
        reqBody.setPrice(10);
//        MvcResult result = mvc.perform(post("/payment/create")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(convertObjectToJsonBytes(reqBody))).andReturn();
        //assertNotEquals("",result.getResponse().getContentAsString());
    }

    @Test
    @Description("")
    void getPayment() {
    }
}
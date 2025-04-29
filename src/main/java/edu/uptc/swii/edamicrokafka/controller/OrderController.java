package edu.uptc.swii.edamicrokafka.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uptc.swii.edamicrokafka.model.CustomerOrder;
import edu.uptc.swii.edamicrokafka.service.OrderEventProducer;
import edu.uptc.swii.edamicrokafka.utils.JsonUtils;


@RestController
public class OrderController {
    @Autowired
    private OrderEventProducer orderEventProducer;
    private static JsonUtils jsonUtils = new JsonUtils();


   @PostMapping("/addOrder")
    public String sendMessageAddCustomer(@RequestBody String custOrder) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            CustomerOrder customerOrderObj = mapper.readValue(custOrder, CustomerOrder.class);
            CustomerOrder customerOrder = new CustomerOrder(customerOrderObj);
            orderEventProducer.sendAddOrderEvent(customerOrder);        
            return "Customer added successfully";
        } catch (JsonProcessingException e) {
            return "Error processing customer: " + e.getMessage();
        }
    }
}

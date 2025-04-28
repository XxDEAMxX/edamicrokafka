package edu.uptc.swii.edamicrokafka.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uptc.swii.edamicrokafka.model.Customer;
import edu.uptc.swii.edamicrokafka.model.CustomerLogin;
import edu.uptc.swii.edamicrokafka.service.CustomerEventProducer;
import edu.uptc.swii.edamicrokafka.utils.JsonUtils;


@RestController
public class CustomerController {
    @Autowired
    private CustomerEventProducer customerEventProducer;
    private static JsonUtils jsonUtils = new JsonUtils();


   @PostMapping("/addcustomer")
    public String sendMessageAddCustomer(@RequestBody String custLog) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            CustomerLogin customerLoginObj = mapper.readValue(custLog, CustomerLogin.class);
            CustomerLogin customerLogin = new CustomerLogin(customerLoginObj);
            customerEventProducer.sendAddCustomerEvent(customerLogin);        
            return "Customer added successfully";
        } catch (Exception e) {
            return "Error processing customer: " + e.getMessage();
        }
    }


    @PostMapping("/editcustomer")
    public String sendMessageEditCustomer(@RequestBody String customer) {
        Customer customerObj = new Customer();
        customerObj = jsonUtils.fromJson(customer, Customer.class);
        customerEventProducer.sendEditCustomerEvent(customerObj);        
        return customerEventProducer.toString();
    }

    @PostMapping("/findbyCustomerid/{id}")
    public String sendMessageAllCustomer(@PathVariable("id") String customerId) {
        customerEventProducer.sendFindByCustomerIDEvent(customerId);        
        return customerEventProducer.toString();
    }

    @GetMapping("/findAllCustomer")
    public String sendFindAllOrders() {
        customerEventProducer.sendFindAllCustomersEvent();        
        return customerEventProducer.toString();
    }
}

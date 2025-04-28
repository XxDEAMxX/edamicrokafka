package edu.uptc.swii.edamicrokafka.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import edu.uptc.swii.edamicrokafka.model.Customer;
import edu.uptc.swii.edamicrokafka.model.CustomerLogin;
import edu.uptc.swii.edamicrokafka.utils.JsonUtils;


@Service
public class CustomerEventConsumer {
   
    @Autowired
    private CustomerService customerService;
    @Autowired
    private LoginService loginService;


    @KafkaListener(topics = "addcustomer_events", groupId = "customer_group")
    public void handleAddOrderEvent(String customer) {
        try {
            JsonUtils jsonUtilsCustomer = new JsonUtils();
            CustomerLogin receiveAddCustomer = jsonUtilsCustomer.fromJson(customer, CustomerLogin.class);
            Customer savedCustomer = customerService.save(receiveAddCustomer.getCustomer());
            receiveAddCustomer.getLogin().setCustomer(savedCustomer);
            loginService.save(receiveAddCustomer.getLogin());
        } catch (Exception e) {
            throw new RuntimeException("Error processing customer event", e);
        }
    }

    @KafkaListener(topics = "editcustomer_events", groupId = "customer_group")
    public void handleEditCustomerEvent(String customer) {
        JsonUtils jsonUtils = new JsonUtils();
        Customer receiveEditCustomer = jsonUtils.fromJson(customer, Customer.class);
        customerService.save(receiveEditCustomer);
    }


    @KafkaListener(topics = "findcustomerbyid_events", groupId = "customer_group")
    public Customer handleFindCustomerByIDEvent(String customer) {
        Customer customerReceived = customerService.findById(customer);
        System.out.println("Customers received: " + customerReceived);
        return customerReceived;
    }


    @KafkaListener(topics = "findallcustomers_events", groupId = "customer_group")
    public List<Customer> handleFindAllCustomers() {
        List<Customer> customersReceived = customerService.findAll();
        System.out.println("Customers received: " + customersReceived);
        System.out.println("Size: " + customersReceived.size());
        return customersReceived;
    }
}

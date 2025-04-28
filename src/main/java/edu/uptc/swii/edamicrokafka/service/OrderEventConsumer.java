package edu.uptc.swii.edamicrokafka.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import edu.uptc.swii.edamicrokafka.model.Customer;
import edu.uptc.swii.edamicrokafka.model.CustomerOrder;
import edu.uptc.swii.edamicrokafka.utils.JsonUtils;


@Service
public class OrderEventConsumer {
   
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;


    @KafkaListener(topics = "addorder_events", groupId = "order_group")
    public void handleAddOrderEvent(String custorder) {
        try {
            JsonUtils jsonUtilsCustomer = new JsonUtils();
            CustomerOrder receiveAddCustomer = jsonUtilsCustomer.fromJson(custorder, CustomerOrder.class);
            Customer savedCustomer = customerService.save(receiveAddCustomer.getCustomer());
            receiveAddCustomer.getOrder().setCustomer(savedCustomer);
            System.out.println("Order saved: " + receiveAddCustomer.getOrder());
            orderService.save(receiveAddCustomer.getOrder());
        } catch (Exception e) {
            throw new RuntimeException("Error processing customer event", e);
        }
    }

    // @KafkaListener(topics = "editcustomer_events", groupId = "order_group")
    // public void handleEditCustomerEvent(String customer) {
    //     JsonUtils jsonUtils = new JsonUtils();
    //     Customer receiveEditCustomer = jsonUtils.fromJson(customer, Customer.class);
    //     customerService.save(receiveEditCustomer);
    // }


    // @KafkaListener(topics = "findcustomerbyid_events", groupId = "order_group")
    // public Customer handleFindCustomerByIDEvent(String customer) {
    //     Customer customerReceived = customerService.findById(customer);
    //     System.out.println("Customers received: " + customerReceived);
    //     return customerReceived;
    // }


    // @KafkaListener(topics = "findallcustomers_events", groupId = "order_group")
    // public List<Customer> handleFindAllCustomers() {
    //     List<Customer> customersReceived = customerService.findAll();
    //     return customersReceived;
    // }
}

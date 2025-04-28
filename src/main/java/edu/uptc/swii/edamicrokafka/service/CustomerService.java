package edu.uptc.swii.edamicrokafka.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import edu.uptc.swii.edamicrokafka.model.Customer;
import edu.uptc.swii.edamicrokafka.repository.CustomerRepository;
import edu.uptc.swii.edamicrokafka.repository.OrderRepository;


@Service
public class CustomerService {
    @Autowired
    private final CustomerRepository customerRepository;


    public CustomerService(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
    }


    public Customer save(Customer customer){
        return customerRepository.saveAndFlush(customer);
    }


    public boolean delete(Customer customer){
        boolean flag = false;
        try{
            customerRepository.delete(customer);
            flag=true;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return flag;
    }


        public Customer findById(String document){
        Customer customer=null;
        Optional<Customer> optionalCustomer = customerRepository.findById(document);
        if (optionalCustomer.isPresent())
            customer = optionalCustomer.get();
        return customer;
    }
    
    public List<Customer> findAll(){
        List<Customer> listCustomer = new ArrayList<Customer>();
        Iterable<Customer> customers = customerRepository.findAll();
        customers.forEach((o) ->{
            listCustomer.add(o);
        });
        return listCustomer;
    }


}

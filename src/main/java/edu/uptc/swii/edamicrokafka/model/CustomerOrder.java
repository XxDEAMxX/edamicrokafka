package edu.uptc.swii.edamicrokafka.model;

public class CustomerOrder {
    
    private Customer customer;
    private Order order;

    public CustomerOrder() {
    }

    public CustomerOrder(Customer customer, Order order) {
        this.customer = customer;
        this.order = order;
    }
    public CustomerOrder(CustomerOrder CustomerOrder) {
        this.customer = CustomerOrder.getCustomer();
        this.order = CustomerOrder.getOrder();
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
}

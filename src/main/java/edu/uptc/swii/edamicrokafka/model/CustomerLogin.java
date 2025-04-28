package edu.uptc.swii.edamicrokafka.model;

public class CustomerLogin {
    
    private Customer customer;
    private Login login;

    public CustomerLogin() {
    }

    public CustomerLogin(Customer customer, Login login) {
        this.customer = customer;
        this.login = login;
    }
    public CustomerLogin(CustomerLogin CustomerLogin) {
        this.customer = CustomerLogin.getCustomer();
        this.login = CustomerLogin.getLogin();
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Login getLogin() {
        return login;
    }
    public void setLogin(Login login) {
        this.login = login;
    }
}

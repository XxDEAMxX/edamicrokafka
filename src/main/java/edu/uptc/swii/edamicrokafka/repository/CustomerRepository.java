package edu.uptc.swii.edamicrokafka.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uptc.swii.edamicrokafka.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {


}

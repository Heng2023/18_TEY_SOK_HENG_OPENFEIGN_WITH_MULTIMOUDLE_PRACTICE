package org.example.service;

import org.example.model.Customer;
import org.example.model.request.CustomerRequest;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(CustomerRequest customerRequest);
    Customer updateCustomer(Long customerId, CustomerRequest customerRequest);
    Customer getCustomer(Long customerId);
    List<Customer> getAllCustomers();
    void deleteCustomer(Long customerId);
}

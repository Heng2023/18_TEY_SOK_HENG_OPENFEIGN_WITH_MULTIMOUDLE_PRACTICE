package org.example.service.serviceimp;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.model.Customer;
import org.example.model.request.CustomerRequest;
import org.example.repository.CustomerRepository;
import org.example.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImp implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(CustomerRequest customerRequest) {
        Customer customer = new Customer();
        customer.setCustomerName(customerRequest.getCustomerName());
        customer.setEmail(customerRequest.getEmail());
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer with ID " + customerId + " not found"));

        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public Customer updateCustomer(Long customerId, CustomerRequest customerRequest) {
        Customer customer = getCustomer(customerId);

        customer.setCustomerName(customerRequest.getCustomerName());
        customer.setEmail(customerRequest.getEmail());

        return customerRepository.save(customer);
    }
}

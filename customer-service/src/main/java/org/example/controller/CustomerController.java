package org.example.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.model.Customer;
import org.example.model.request.CustomerRequest;
import org.example.model.response.ApiResponse;
import org.example.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<ApiResponse<Customer>> createCustomer(@RequestBody CustomerRequest customerRequest) {
        Customer customer = customerService.createCustomer(customerRequest);
        ApiResponse<Customer> apiResponse = new ApiResponse<>(
                "A customer has been created successfully.",
                customer,
                HttpStatus.CREATED,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<ApiResponse<Customer>> updateCustomer(@PathVariable Long customerId, @RequestBody CustomerRequest customerRequest) {
        Customer customer = customerService.updateCustomer(customerId, customerRequest);
         ApiResponse<Customer> apiResponse = new ApiResponse<>(
                "A customer has been updated successfully.",
                customer,
                HttpStatus.OK,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<ApiResponse<Customer>> getCustomer(@PathVariable Long customerId) {
        Customer customer = customerService.getCustomer(customerId);
        ApiResponse<Customer> apiResponse = new ApiResponse<>(
                "A customer has been fetched successfully.",
                customer,
                HttpStatus.OK,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Customer>>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        ApiResponse<List<Customer>> apiResponse = new ApiResponse<>(
                "Customers have been fetched successfully.",
                customers,
                HttpStatus.OK,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<ApiResponse<?>> deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);

        ApiResponse<List<Customer>> apiResponse = new ApiResponse<>(
                "A customer has been deleted successfully.",
                null,
                HttpStatus.OK,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}

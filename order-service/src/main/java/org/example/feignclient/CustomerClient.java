package org.example.feignclient;

import org.example.model.response.ApiResponse;
import org.example.model.response.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", url = "http://localhost:9091/api/v1/customers")
public interface CustomerClient {
    @GetMapping("/{customerId}")
    ResponseEntity<ApiResponse<CustomerResponse>> getCustomer(@PathVariable Long customerId);
}

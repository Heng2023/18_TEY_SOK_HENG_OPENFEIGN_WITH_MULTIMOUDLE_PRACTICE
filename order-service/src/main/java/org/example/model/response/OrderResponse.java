package org.example.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderResponse {
    private Long orderId;
    private CustomerResponse customerDetails;
    private List<ProductResponse> productDetails;
    private LocalDate orderDate;
}

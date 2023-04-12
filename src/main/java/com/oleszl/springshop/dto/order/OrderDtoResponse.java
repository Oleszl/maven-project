package com.oleszl.springshop.dto.order;

import com.oleszl.springshop.dto.customer.CustomerDto;
import com.oleszl.springshop.dto.product.ProductDto;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class OrderDtoResponse {
    private Long id;
    private Set<ProductDto> products = new HashSet<>();
    private CustomerDto customer;
}

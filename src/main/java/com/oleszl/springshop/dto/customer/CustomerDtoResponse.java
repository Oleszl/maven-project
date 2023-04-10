package com.oleszl.springshop.dto.customer;

import com.oleszl.springshop.entity.Order;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class CustomerDtoResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<Order> orders = new HashSet<>();

}

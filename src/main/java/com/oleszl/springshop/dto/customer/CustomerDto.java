package com.oleszl.springshop.dto.customer;

import lombok.Data;

@Data
public class CustomerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}

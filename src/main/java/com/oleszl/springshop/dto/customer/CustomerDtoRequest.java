package com.oleszl.springshop.dto.customer;

import lombok.Data;

@Data
public class CustomerDtoRequest {
    private String firstName;
    private String lastName;
    private String email;
}

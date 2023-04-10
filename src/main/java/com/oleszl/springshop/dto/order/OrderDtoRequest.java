package com.oleszl.springshop.dto.order;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class OrderDtoRequest {
    private Long customerId;
    private Set<OrderProductIdDto> products = new HashSet<>();
}

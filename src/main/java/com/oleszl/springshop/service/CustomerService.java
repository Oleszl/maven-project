package com.oleszl.springshop.service;

import com.oleszl.springshop.dto.customer.CustomerDtoRequest;
import com.oleszl.springshop.dto.customer.CustomerDtoResponse;

public interface CustomerService {

    CustomerDtoResponse save(CustomerDtoRequest customerRequestDto);

    CustomerDtoResponse updateById(CustomerDtoRequest customerRequestDto, Long customerId);

    void deleteById(Long id);

    CustomerDtoResponse getById(Long id);
}

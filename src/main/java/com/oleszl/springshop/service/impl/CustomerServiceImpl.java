package com.oleszl.springshop.service.impl;

import com.oleszl.springshop.dto.customer.CustomerDtoRequest;
import com.oleszl.springshop.dto.customer.CustomerDtoResponse;
import com.oleszl.springshop.entity.Customer;
import com.oleszl.springshop.exception.ResourceNotFoundException;
import com.oleszl.springshop.repository.CustomerRepository;
import com.oleszl.springshop.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final ModelMapper mapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper mapper) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    @Override
    public CustomerDtoResponse save(CustomerDtoRequest customerRequestDto) {
        Customer customer = mapper.map(customerRequestDto, Customer.class);
        return mapper.map(customerRepository.save(customer), CustomerDtoResponse.class);
    }

    @Override
    public CustomerDtoResponse updateById(CustomerDtoRequest customerRequestDto, Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));

        customer.setFirstName(customerRequestDto.getFirstName());
        customer.setLastName(customerRequestDto.getLastName());
        customer.setEmail(customerRequestDto.getEmail());

        Customer updatedCustomer = customerRepository.save(customer);
        return mapper.map(updatedCustomer, CustomerDtoResponse.class);
    }

    @Override
    public void deleteById(Long customerId) {
        customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
        customerRepository.deleteById(customerId);
    }

    @Override
    public CustomerDtoResponse getById(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
        return mapper.map(customer, CustomerDtoResponse.class);
    }
}

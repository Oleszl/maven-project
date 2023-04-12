package com.oleszl.springshop.controller;

import com.oleszl.springshop.dto.customer.CustomerDtoRequest;
import com.oleszl.springshop.dto.customer.CustomerDtoResponse;
import com.oleszl.springshop.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public CustomerDtoResponse saveCustomer(@RequestBody CustomerDtoRequest customerRequestDto) {
        return customerService.save(customerRequestDto);
    }

    @PutMapping("/{id}")
    public CustomerDtoResponse updateCustomer(@RequestBody CustomerDtoRequest customerRequestDto, @PathVariable Long id) {
        return customerService.updateById(customerRequestDto, id);
    }

    @GetMapping("/{id}")
    public CustomerDtoResponse getCustomerById(@PathVariable Long id) {
        return customerService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomerById(@PathVariable Long id) {
        customerService.deleteById(id);
    }
}

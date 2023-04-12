package com.oleszl.springshop.controller;

import com.oleszl.springshop.dto.order.OrderDtoRequest;
import com.oleszl.springshop.dto.order.OrderDtoResponse;
import com.oleszl.springshop.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public OrderDtoResponse createUserOrder(@RequestBody OrderDtoRequest orderDtoRequest) {
        return orderService.create(orderDtoRequest);
    }

    @GetMapping("/{id}")
    public OrderDtoResponse getById(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomerById(@PathVariable Long id) {
        orderService.deleteById(id);
    }

    @PutMapping("/{id}")
    public OrderDtoResponse updateCustomer(@RequestBody OrderDtoRequest orderDtoRequest, @PathVariable Long id) {
        return orderService.updateById(orderDtoRequest, id);
    }
}

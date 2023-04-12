package com.oleszl.springshop.service;


import com.oleszl.springshop.dto.order.OrderDtoRequest;
import com.oleszl.springshop.dto.order.OrderDtoResponse;

public interface OrderService {
    OrderDtoResponse create(OrderDtoRequest orderDtoRequest);

    OrderDtoResponse updateById(OrderDtoRequest orderDtoRequest, Long id);

    void deleteById(Long orderId);

    OrderDtoResponse getById(Long id);

}

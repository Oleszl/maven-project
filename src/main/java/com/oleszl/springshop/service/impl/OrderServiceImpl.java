package com.oleszl.springshop.service.impl;

import com.oleszl.springshop.dto.order.OrderDtoRequest;
import com.oleszl.springshop.dto.order.OrderDtoResponse;
import com.oleszl.springshop.dto.order.OrderProductIdDto;
import com.oleszl.springshop.entity.Customer;
import com.oleszl.springshop.entity.Order;
import com.oleszl.springshop.entity.Product;
import com.oleszl.springshop.exception.ResourceNotFoundException;
import com.oleszl.springshop.repository.CustomerRepository;
import com.oleszl.springshop.repository.OrderRepository;
import com.oleszl.springshop.repository.ProductRepository;
import com.oleszl.springshop.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    private final ModelMapper mapper;

    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository,
                            CustomerRepository customerRepository, ModelMapper mapper) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    @Override
    public OrderDtoResponse create(OrderDtoRequest orderDtoRequest) {
        Set<Product> products = getAllProductsByOrder(orderDtoRequest);
        Customer customer = customerRepository.findById(orderDtoRequest.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", orderDtoRequest.getCustomerId()));

        Order userOrder = new Order();
        userOrder.setCustomer(customer);
        userOrder.setProducts(products);

        return mapper.map(orderRepository.save(userOrder), OrderDtoResponse.class);
    }

    private Set<Product> getAllProductsByOrder(OrderDtoRequest orderDtoRequest) {
        Set<Product> products = new HashSet<>();
        for (OrderProductIdDto order : orderDtoRequest.getProducts()) {
            Product product = productRepository.findById(order.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product", "id", order.getProductId()));
            products.add(product);
        }
        return products;
    }

    @Override
    public OrderDtoResponse updateById(OrderDtoRequest orderDtoRequest, Long orderId) {
        Order userOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));

        Set<Product> products = getAllProductsByOrder(orderDtoRequest);

        Customer customer = customerRepository.findById(orderDtoRequest.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", orderDtoRequest.getCustomerId()));
        userOrder.setProducts(products);
        userOrder.setCustomer(customer);
        Order savedOrder = orderRepository.save(userOrder);
        return mapper.map(savedOrder, OrderDtoResponse.class);
    }

    @Override
    public void deleteById(Long orderId) {
        orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
        orderRepository.deleteById(orderId);
    }

    @Override
    public OrderDtoResponse getById(Long orderId) {
        Order userOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
        return mapper.map(userOrder, OrderDtoResponse.class);
    }
}

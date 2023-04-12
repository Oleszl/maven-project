package com.oleszl.springshop.service.impl;


import com.oleszl.springshop.dto.product.ProductDtoRequest;
import com.oleszl.springshop.dto.product.ProductDtoResponse;
import com.oleszl.springshop.entity.Order;
import com.oleszl.springshop.entity.Product;
import com.oleszl.springshop.exception.ResourceNotFoundException;
import com.oleszl.springshop.repository.ProductRepository;
import com.oleszl.springshop.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public ProductDtoResponse create(ProductDtoRequest productDtoRequest) {
        Product product = mapper.map(productDtoRequest, Product.class);
        return mapper.map(productRepository.save(product), ProductDtoResponse.class);
    }

    @Override
    public ProductDtoResponse updateById(ProductDtoRequest productDtoRequest, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        product.setName(productDtoRequest.getName());
        product.setPrice(productDtoRequest.getPrice());
        return mapper.map(productRepository.save(product), ProductDtoResponse.class);
    }

    @Override
    public void deleteById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        Set<Order> orders = product.getOrders();
        orders.forEach(o -> {
            Set<Product> products = o.getProducts();
            products.remove(product);
            o.setProducts(products);
        });
        productRepository.delete(product);
    }

    @Override
    public ProductDtoResponse getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        return mapper.map(product, ProductDtoResponse.class);
    }
}

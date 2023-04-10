package com.oleszl.springshop.service;


import com.oleszl.springshop.dto.product.ProductDtoRequest;
import com.oleszl.springshop.dto.product.ProductDtoResponse;

public interface ProductService {
    ProductDtoResponse create(ProductDtoRequest productDtoRequest);

    ProductDtoResponse updateById(ProductDtoRequest productDtoRequest, Long id);

    void deleteById(Long productId);

    ProductDtoResponse getById(Long id);
}

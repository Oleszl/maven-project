package com.oleszl.service;


import com.oleszl.model.Product;

public interface ProductService {
    int create(Product product);

    Product getById(long id);

    int update(Product product);

    int deleteById(long id);
}

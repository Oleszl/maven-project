package com.oleszl.dao;


import com.oleszl.model.Product;

public interface ProductDAO {
    int create(Product product);

    Product getById(long id);

    int update(long id, Product product);

    int deleteById(long id);

}

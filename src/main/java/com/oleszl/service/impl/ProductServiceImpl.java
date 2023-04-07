package com.oleszl.service.impl;


import com.oleszl.dao.ProductDAO;
import com.oleszl.dao.impl.ProductDAOImpl;
import com.oleszl.model.Product;
import com.oleszl.service.ProductService;

public class ProductServiceImpl implements ProductService {
    private ProductDAO productDAO = new ProductDAOImpl();

    @Override
    public int create(Product product) {
        return productDAO.create(product);
    }

    @Override
    public Product getById(long id) {
        return productDAO.getById(id);
    }

    @Override
    public int update(long id, Product product) {
        return productDAO.updateById(id, product);
    }

    @Override
    public int deleteById(long id) {
        Product product = productDAO.getById(id);
        if (product == null) {
            return 404;
        }
        return productDAO.deleteById(id);
    }

}

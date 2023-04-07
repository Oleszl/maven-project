package com.oleszl.dao.impl;

import com.oleszl.config.MySQLConnector;
import com.oleszl.dao.ProductDAO;
import com.oleszl.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAOImpl implements ProductDAO {
    @Override
    public int create(Product product) {
        int status = 400;
        String SQL = "INSERT INTO product(name, price) VALUES (?, ?)";

        try (
                Connection connection = MySQLConnector.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        ) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setBigDecimal(2, product.getPrice());

            preparedStatement.execute();
            status = 201;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

    @Override
    public Product getById(long id) {
        String SQL = "SELECT name, price FROM product WHERE id =?";
        Product product = null;

        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        ) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product = new Product();
                product.setId(id);
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getBigDecimal(("price")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public int updateById(long id, Product product) {
        String SQL = "UPDATE product set name = ?, price = ? WHERE id =?";
        int status = 404;

        try (
                Connection connection = MySQLConnector.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        ) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setBigDecimal(2, product.getPrice());
            preparedStatement.setLong(3, id);

            preparedStatement.execute();
            status = 200;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

    @Override
    public int deleteById(long id) {
        int status = 404;
        String SQL = "delete from product where id=?";

        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        ) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            status = 200;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

}

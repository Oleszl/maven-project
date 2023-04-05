package com.oleszl.servlet;


import com.oleszl.model.Product;
import com.oleszl.service.ProductService;
import com.oleszl.service.impl.ProductServiceImpl;
import com.oleszl.util.RestUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/product/*")
public class ProductServlet extends HttpServlet {
    private ProductService productService = new ProductServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = RestUtil.getFromJson(req, Product.class);
        int status = productService.create(product);
        resp.setStatus(status);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] pathInfo = null;
        try {
            pathInfo = req.getPathInfo().split("/");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Product ID not provided");
        }
        int id = Integer.parseInt(pathInfo[1]);
        Product product = productService.getById(id);
        if (product == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Product not found with provided id");
        } else {
            String respJson = RestUtil.toJson(product);
            resp.getWriter().write(respJson);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] pathInfo = null;
        try {
            pathInfo = req.getPathInfo().split("/");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Product ID not provided");
        }
        int id = Integer.parseInt(pathInfo[1]);
        int status = productService.deleteById(id);
        if (status == 404) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().write("Product not found with provided id");
        } else {
            String respJson = RestUtil.toJson("Product deleted successfully");
            resp.getWriter().write(respJson);
        }
    }
}

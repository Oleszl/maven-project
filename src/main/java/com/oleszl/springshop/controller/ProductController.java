package com.oleszl.springshop.controller;

import com.oleszl.springshop.dto.product.ProductDtoRequest;
import com.oleszl.springshop.dto.product.ProductDtoResponse;
import com.oleszl.springshop.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductDtoResponse getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PostMapping
    public ProductDtoResponse createProduct(@RequestBody ProductDtoRequest productDtoRequest) {
        return productService.create(productDtoRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomerById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ProductDtoResponse updateCustomer(@RequestBody ProductDtoRequest productDtoRequest, @PathVariable Long id) {
        return productService.updateById(productDtoRequest, id);
    }
}

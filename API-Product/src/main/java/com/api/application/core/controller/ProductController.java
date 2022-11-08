package com.api.application.core.controller;

import com.api.application.core.model.Product;
import com.api.application.core.repository.ProductRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Operation(
            summary = "Create a new Product",
            description = "Create a new Product"
    )
    @PostMapping(
            value = "/create",
            consumes = "application/json",
            produces = "application/json"
    )
    public Product create(
            @RequestBody Product bodyRequest
    ) {
        Product product = Product.builder().name(bodyRequest.getName()).build();
        productRepository.save(product);

        return product;
    }

    @Operation(
            summary = "List Products",
            description = "List Products"
    )
    @GetMapping(
            value = ""
    )
    public List<Product> list(

    ) {
        return productRepository.findAll();
    }

    @Operation(
            summary = "Get Product by Id",
            description = "Get Product by Id"
    )
    @GetMapping(
            value = "{id}"
    )
    public Product get(
            @PathVariable(value = "id") Long id
    ) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    @Operation(
            summary = "Delete Product by Id",
            description = "Delete Product by Id"
    )
    @DeleteMapping(
            value = "{id}"
    )
    public void delete(
            @PathVariable(value = "id") Long id
    ) {
        productRepository.deleteById(id);
    }

}

package com.example.invoicedemo.service;

import com.example.invoicedemo.exceptions.ProductNotFoundException;
import com.example.invoicedemo.model.Product;
import com.example.invoicedemo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProductsList() {
        return productRepository.findAll();
    }

    public boolean isUsed(Long id) {
        return productRepository.isUsed(id) > 0 ? true : false;
    }

    public Result saveProduct(Product product) {
            //  check if product with the name already exist  -  Uniqe fields
            if (product.getId() == null) {
                Optional<Product> foundProduct = productRepository.findByName(product.getName());
                if (foundProduct.isPresent()) {
                    return new Result(product, "Product :" + product.getName() + " already exists !");
                }
            }

            Product savedProduct = productRepository.save(product);
            return  new Result(savedProduct);
    }

    public void deleteProduct(Long id){
            productRepository.deleteById(id);
    }

    public Product getProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new ProductNotFoundException("Product with id =" + id + "not found");
        }
        return product.get();
    }
}

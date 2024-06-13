package dev.armancodeblock.springtdd.service;


import dev.armancodeblock.springtdd.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product save(Product product);
    Optional<Product> findById(Integer id);
    List<Product> findAll();
    void deleteById(Integer id);
    Product updateById(Integer id,Product product);
}

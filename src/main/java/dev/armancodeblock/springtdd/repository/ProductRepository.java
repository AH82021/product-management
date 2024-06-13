package dev.armancodeblock.springtdd.repository;

import dev.armancodeblock.springtdd.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {

}

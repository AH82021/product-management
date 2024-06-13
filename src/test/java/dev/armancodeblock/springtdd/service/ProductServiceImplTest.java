package dev.armancodeblock.springtdd.service;

import static org.junit.jupiter.api.Assertions.*;

import dev.armancodeblock.springtdd.entity.Product;
import dev.armancodeblock.springtdd.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product("Sample Product","A sample description", 10,100.0);
    }

    @Test
    public void testServiceAnnotation() {
        Service serviceAnnotation = ProductServiceImpl.class.getAnnotation(Service.class);
        assertNotNull(serviceAnnotation, "ProductServiceImpl should be annotated with @Service");
    }

    @Test
    public void testSave() {
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product savedProduct = productService.save(product);
        assertNotNull(savedProduct, "Saved product should not be null");
        assertEquals("Sample Product", savedProduct.getName(), "Product name should match");
    }

    @Test
    public void testFindById() {
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));

        Optional<Product> foundProduct = productService.findById(1);
        assertTrue(foundProduct.isPresent(), "Product should be found");
        assertEquals("Sample Product", foundProduct.get().getName(), "Product name should match");
    }

    @Test
    public void testFindAll() {
        when(productRepository.findAll()).thenReturn(List.of(product));

        List<Product> products = productService.findAll();
        assertFalse(products.isEmpty(), "Product list should not be empty");
        assertEquals(1, products.size(), "Product list size should be 1");
    }

    @Test
    public void testDeleteById() {
        doNothing().when(productRepository).deleteById(anyInt());

        productService.deleteById(1);
        verify(productRepository, times(1)).deleteById(1);
    }

    @Test
    public void testUpdate() {
        // Mocking findById to return the existing product
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));

        // Mocking save to return the updated product
        Product updatedProduct = new Product("Updated Product","Updated Description",20,200);
        updatedProduct.setId(1);
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

        // Performing the update
        Product result = productService.updateById(1, updatedProduct);

        // Verifying the result
        assertNotNull(result, "Updated product should not be null");
        assertEquals(1, result.getId(), "Product ID should match");
        assertEquals("Updated Product", result.getName(), "Product name should match");
        assertEquals("Updated Description", result.getDescription(), "Product description should match");
        assertEquals(20, result.getQuantity(), "Product quantity should match");
        assertEquals(200.0, result.getPrice(), "Product price should match");
    }
}

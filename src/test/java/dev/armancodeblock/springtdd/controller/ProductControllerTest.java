package dev.armancodeblock.springtdd.controller;

import static org.junit.jupiter.api.Assertions.*;

import dev.armancodeblock.springtdd.entity.Product;
import dev.armancodeblock.springtdd.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product("Sample Product","A sample description", 10,100.0);
        product.setId(1);
    }

    @Test
    public void testCreateProduct() throws Exception {
        when(productService.save(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Sample Product\", \"description\": \"A sample description\", \"quantity\": 10, \"price\": 100.0}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Sample Product"));
    }

    @Test
    public void testGetProductById() throws Exception {
        when(productService.findById(anyInt())).thenReturn(Optional.of(product));

        mockMvc.perform(get("/products/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Sample Product"));
    }

    @Test
    public void testGetAllProducts() throws Exception {
        when(productService.findAll()).thenReturn(Arrays.asList(product));

        mockMvc.perform(get("/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Sample Product"));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        when(productService.updateById(anyInt(), any(Product.class))).thenReturn(product);

        mockMvc.perform(put("/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Updated Product\", \"description\": \"Updated description\", \"quantity\": 20, \"price\": 200.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Sample Product")); // This should be "Updated Product" if your update method correctly maps the new values
    }

    @Test
    public void testDeleteProduct() throws Exception {
        mockMvc.perform(delete("/products/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}

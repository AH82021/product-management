package dev.armancodeblock.springtdd.service;



import dev.armancodeblock.springtdd.entity.Product;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductServiceTest {

    @Test
    public void testSaveMethod() throws NoSuchMethodException {
        Method saveMethod = ProductService.class.getMethod("save", Product.class);
        assertTrue(saveMethod != null, "save method should be present");
    }

    @Test
    public void testFindByIdMethod() throws NoSuchMethodException {
        Method findByIdMethod = ProductService.class.getMethod("findById", Integer.class);
        assertTrue(findByIdMethod != null, "findById method should be present");
    }

    @Test
    public void testFindAllMethod() throws NoSuchMethodException {
        Method findAllMethod = ProductService.class.getMethod("findAll");
        assertTrue(findAllMethod != null, "findAll method should be present");
    }

    @Test
    public void testDeleteByIdMethod() throws NoSuchMethodException {
        Method deleteByIdMethod = ProductService.class.getMethod("deleteById", Integer.class);
        assertTrue(deleteByIdMethod != null, "deleteById method should be present");
    }

    @Test
    public void testUpdateMethod() throws NoSuchMethodException {
        Method updateMethod = ProductService.class.getMethod("updateById", Integer.class, Product.class);
        assertTrue(updateMethod != null, "update method should be present");
    }

    @Test
    public void testAllCRUDMethods() {
        List<String> expectedMethods = Arrays.asList("save", "findById", "findAll", "deleteById", "updateById");
        Method[] methods = ProductService.class.getDeclaredMethods();

        for (String methodName : expectedMethods) {
            boolean methodFound = Arrays.stream(methods).anyMatch(method -> method.getName().equals(methodName));
            assertTrue(methodFound, methodName + " method should be present");
        }
    }
}

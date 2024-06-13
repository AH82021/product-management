package dev.armancodeblock.springtdd.entity;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.junit.jupiter.api.Test;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductAnnotationTest {

    @Test
    public void testEntityAnnotation() {
        Entity entityAnnotation = Product.class.getAnnotation(Entity.class);
        assertNotNull(entityAnnotation, "Product class should be annotated with @Entity");
    }

    @Test
    public void testIdAnnotation() throws NoSuchFieldException {
        Field idField = Product.class.getDeclaredField("id");
        Id idAnnotation = idField.getAnnotation(Id.class);
        assertNotNull(idAnnotation, "id field should be annotated with @Id");
    }

    @Test
    public void testGeneratedValueAnnotation() throws NoSuchFieldException {
        Field idField = Product.class.getDeclaredField("id");
        GeneratedValue generatedValueAnnotation = idField.getAnnotation(GeneratedValue.class);
        assertNotNull(generatedValueAnnotation, "id field should be annotated with @GeneratedValue");
    }

    @Test
    public void testFieldAnnotations() throws NoSuchFieldException {
        Field nameField = Product.class.getDeclaredField("name");
        assertNotNull(nameField.getAnnotation(jakarta.persistence.Column.class), "name field should be annotated with @Column");

        Field descriptionField = Product.class.getDeclaredField("description");
        assertNotNull(descriptionField.getAnnotation(jakarta.persistence.Column.class), "description field should be annotated with @Column");

        Field quantityField = Product.class.getDeclaredField("quantity");
        assertNotNull(quantityField.getAnnotation(jakarta.persistence.Column.class), "quantity field should be annotated with @Column");

        Field priceField = Product.class.getDeclaredField("price");
        assertNotNull(priceField.getAnnotation(jakarta.persistence.Column.class), "price field should be annotated with @Column");
    }
    @Test
    public void testEmptyConstructor() {
        Constructor<?>[] constructors = Product.class.getDeclaredConstructors();
        boolean hasEmptyConstructor = Arrays.stream(constructors)
                .anyMatch(constructor -> constructor.getParameterCount() == 0);
        assertTrue(hasEmptyConstructor, "Product class should have an empty (no-argument) constructor");
    }

}

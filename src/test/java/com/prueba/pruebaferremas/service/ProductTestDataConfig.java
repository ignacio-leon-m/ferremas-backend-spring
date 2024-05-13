package com.prueba.pruebaferremas.service;

import com.prueba.pruebaferremas.model.Product;
import com.prueba.pruebaferremas.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;

import java.math.BigDecimal;
import java.util.Arrays;

@TestConfiguration
@Transactional
@DataJpaTest
public class ProductTestDataConfig {

    @Autowired
    private ProductRepository productRepository;

    //Método que se ejecuta antes de cada prueba
    @BeforeEach
    public void populateTestData() {
        // Insertar datos de prueba en la base de datos
        Product product1 = new Product("Producto 1", BigDecimal.valueOf(10.99), "PROD-123", "Electrodoméstico");
        Product product2 = new Product("Producto 2", BigDecimal.valueOf(15.50), "PROD-456", "Electrónica");

        productRepository.saveAll(Arrays.asList(product1, product2));
    }
}

package com.prueba.pruebaferremas.repository;

import com.prueba.pruebaferremas.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Decorador de Spring para indicar que es un repositorio
public interface ProductRepository extends JpaRepository<Product, Long>
{

}

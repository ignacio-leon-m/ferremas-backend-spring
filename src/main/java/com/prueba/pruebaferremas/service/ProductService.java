package com.prueba.pruebaferremas.service;

import com.prueba.pruebaferremas.dto.product.ProductDTO;
import com.prueba.pruebaferremas.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Decorador de Spring para indicar que es un servicio
public interface ProductService {

    //Éste método se encarga de listar todos los productos de la base de datos
    public List<ProductDTO> listAllProducts();
    //Éste método se encarga de agregar un producto a la base de datos
    public ProductDTO addProduct(ProductDTO productDTO);
}

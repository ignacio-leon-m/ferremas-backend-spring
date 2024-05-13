package com.prueba.pruebaferremas.service;

import com.prueba.pruebaferremas.dto.product.ProductDTO;
import com.prueba.pruebaferremas.model.Product;
import com.prueba.pruebaferremas.repository.ProductRepository;
import com.zaxxer.hikari.util.FastList;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    //Variable de tipo ProductRepository que se encargará de realizar las operaciones con la base de datos
    private ProductRepository productRepository;

    //Constructor de la clase, recibe un objeto de tipo ProductRepository y lo asigna a la variable productRepository
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<ProductDTO> listAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product : products) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setNombre(product.getNombre());
            productDTO.setPrecio(product.getPrecio());
            productDTO.setCodigo(product.getCodigo());
            productDTO.setClasificacion(product.getClasificacion());
            productDTO.setHabilitado(product.getHabilitado());
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setNombre(productDTO.getNombre());
        product.setPrecio(productDTO.getPrecio());
        product.setCodigo(productDTO.getCodigo());
        product.setClasificacion(productDTO.getClasificacion());
        product.setHabilitado(productDTO.getHabilitado());
        productRepository.save(product);
        productDTO.setId(product.getId());
        return productDTO;
    }
}

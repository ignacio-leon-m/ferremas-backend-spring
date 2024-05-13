package com.prueba.pruebaferremas.controller;

import com.prueba.pruebaferremas.dto.product.ProductDTO;
import com.prueba.pruebaferremas.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("api/product")
public class ProductController {
    //Variable de tipo ProductService que se encargará de realizar las operaciones con los productos
    private ProductService productService;
    //Constructor de la clase, recibe un objeto de tipo ProductService y lo asigna a la variable productService
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //Método que se encarga de listar todos los productos de la base de datos
    @GetMapping(value = "/list", produces = "application/json")
    public ResponseEntity<List<ProductDTO>> getProducts() {
        List<ProductDTO> productDTOS = productService.listAllProducts();
        return ResponseEntity.ok(productDTOS);
    }

    //Método que agrega productos a la base de datos a través de API o un formulario
    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO productDTO1 = productService.addProduct(productDTO);
        return ResponseEntity.ok(productDTO1);
    }


}

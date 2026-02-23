package fi.metropolia.teemuerk.webstoreapi.controller;

import fi.metropolia.teemuerk.webstoreapi.entity.Products;
import fi.metropolia.teemuerk.webstoreapi.repository.ProductsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private final ProductsRepository repository;
    public ProductsController(ProductsRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Products>getProductById(@PathVariable Integer id){
        return repository.findById(id).map(product -> ResponseEntity.ok(product)).orElse(ResponseEntity.notFound().build());
    }
}

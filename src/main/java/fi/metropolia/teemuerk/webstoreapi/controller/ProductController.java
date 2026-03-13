package fi.metropolia.teemuerk.webstoreapi.controller;

import fi.metropolia.teemuerk.webstoreapi.dto.ProductDto;
import fi.metropolia.teemuerk.webstoreapi.entity.Product;
import fi.metropolia.teemuerk.webstoreapi.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }



    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDto>getProductById(@PathVariable Integer id){
        ProductDto productDto = productService.getProductDto(id);
        if (productDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>>getProducts(){
        List<ProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<ProductDto>>getProductsByCategoryId(@PathVariable int id) {
        List<ProductDto> products = productService.getProductsByCategoryId(id);
        return ResponseEntity.ok(products);
    }
}

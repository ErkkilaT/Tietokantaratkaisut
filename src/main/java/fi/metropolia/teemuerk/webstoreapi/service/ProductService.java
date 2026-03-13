package fi.metropolia.teemuerk.webstoreapi.service;

import fi.metropolia.teemuerk.webstoreapi.dto.ProductDto;
import fi.metropolia.teemuerk.webstoreapi.entity.Product;
import fi.metropolia.teemuerk.webstoreapi.repository.ProductRepository;
import fi.metropolia.teemuerk.webstoreapi.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }



    public ProductDto getProductDto(Integer id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return null;
        }
        return productMapper.toDto(product);
    }

    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAllProducts();
        List<ProductDto> dtos = new ArrayList<>();
        for(Product product : products) {
            ProductDto dto = productMapper.toDto(product);
            dtos.add(dto);
        }
        return dtos;
    }

    public List<ProductDto> getProductsByCategoryId(int id) {
        List<Product> products = productRepository.findProductsByCategoryId(id);
        List<ProductDto> dtos = new ArrayList<>();
        for(Product product : products) {
            ProductDto dto = productMapper.toDto(product);
            dtos.add(dto);
        }
        return dtos;
    }

}

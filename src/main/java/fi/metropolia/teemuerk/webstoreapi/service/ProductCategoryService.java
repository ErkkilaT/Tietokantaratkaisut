package fi.metropolia.teemuerk.webstoreapi.service;

import fi.metropolia.teemuerk.webstoreapi.entity.ProductCategory;
import fi.metropolia.teemuerk.webstoreapi.repository.ProductCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryService {
    private final ProductCategoryRepository repository;

    public ProductCategoryService(ProductCategoryRepository repository) {
        this.repository = repository;
    }

    public List<ProductCategory> getAllCategories() {
        return repository.findAll();
    }
}

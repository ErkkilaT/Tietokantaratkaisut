package fi.metropolia.teemuerk.webstoreapi.service;

import fi.metropolia.teemuerk.webstoreapi.entity.Products;
import fi.metropolia.teemuerk.webstoreapi.repository.ProductsRepository;

import java.util.List;

public class ProductsService {
    public List<Products> getAllProducts() {
        List<Products> products = ProductsRepository.findAllProducts();
    }
}

package fi.metropolia.teemuerk.webstoreapi.mapper;

import fi.metropolia.teemuerk.webstoreapi.dto.ProductDto;
import fi.metropolia.teemuerk.webstoreapi.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDto toDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setStock_quantity(product.getStock_quantity());
        productDto.setCategory_id(product.getCategory_id());
        return productDto;
    }
    public Product toEntity(ProductDto dto) {
        Product product = new Product();

        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setStock_quantity(dto.getStock_quantity());
        product.setCategory_id(dto.getCategory_id());

        return product;
    }
}

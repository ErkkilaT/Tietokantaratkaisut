package fi.metropolia.teemuerk.webstoreapi.repository;

import fi.metropolia.teemuerk.webstoreapi.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
}
package fi.metropolia.teemuerk.webstoreapi.repository;

import fi.metropolia.teemuerk.webstoreapi.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Products, Integer> {

    @Query("SELECT p FROM Products p")
    List<Products> findAllProducts();
}

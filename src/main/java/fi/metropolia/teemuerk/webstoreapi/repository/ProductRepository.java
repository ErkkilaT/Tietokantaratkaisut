package fi.metropolia.teemuerk.webstoreapi.repository;

import fi.metropolia.teemuerk.webstoreapi.entity.Product;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p")
    List<Product> findAllProducts();

    @Query("SELECT p FROM Product p WHERE p.category_id = :id")
    List<Product> findProductsByCategoryId(@Param("id")int id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from Product p where p.id = :id")
    Product findByIdForUpdate(Integer id);

}

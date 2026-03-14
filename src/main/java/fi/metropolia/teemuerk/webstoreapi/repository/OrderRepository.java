package fi.metropolia.teemuerk.webstoreapi.repository;

import fi.metropolia.teemuerk.webstoreapi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository  extends JpaRepository<Order, Integer> {
    @Query("SELECT o FROM Order o WHERE o.customer.id = :id")
    List<Order> findByCustomerId(@Param("id")int id);

    @Query("SELECT o FROM Order o WHERE o.shipping_address.id = :id")
    List<Order> findByAddressId(@Param("id")int id);

}

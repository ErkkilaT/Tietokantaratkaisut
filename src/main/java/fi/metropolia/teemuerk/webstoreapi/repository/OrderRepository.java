package fi.metropolia.teemuerk.webstoreapi.repository;


import fi.metropolia.teemuerk.webstoreapi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<Order, Integer> {
}

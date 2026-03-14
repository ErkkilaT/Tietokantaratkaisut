package fi.metropolia.teemuerk.webstoreapi.repository;

import fi.metropolia.teemuerk.webstoreapi.entity.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerAddressRepository  extends JpaRepository<CustomerAddress, Integer> {
    @Query("SELECT p FROM CustomerAddress p WHERE p.customer_id = :id")
    List<CustomerAddress> findByCustomerId(@Param("id")int id);
}

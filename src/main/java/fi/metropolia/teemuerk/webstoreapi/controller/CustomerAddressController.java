package fi.metropolia.teemuerk.webstoreapi.controller;

import fi.metropolia.teemuerk.webstoreapi.entity.CustomerAddress;
import fi.metropolia.teemuerk.webstoreapi.service.CustomerAddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/address")
public class CustomerAddressController {
    private final CustomerAddressService customerAddressService;
    public CustomerAddressController( CustomerAddressService customerAddressService) {
        this.customerAddressService = customerAddressService;
    }

    @GetMapping("/{customer_id}")
    public ResponseEntity<List<CustomerAddress>>getCustomerAddresses(@PathVariable Integer customer_id){
        List<CustomerAddress> products = customerAddressService.getCustomerAddresses(customer_id);

        if(products == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(products);
    }

    @PatchMapping("/{id}") //address id
    public ResponseEntity<CustomerAddress> updateCustomerAddress(@PathVariable Integer id, @RequestBody CustomerAddress customerAddress) {
        CustomerAddress updatedCustomerAddress = customerAddressService.updateCustomerAddress(id, customerAddress);
        if (updatedCustomerAddress == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCustomerAddress);
    }

    @PostMapping
    public ResponseEntity<CustomerAddress> addCustomerAddress(@RequestBody CustomerAddress customerAddress) {
        CustomerAddress savedCustomerAddress = customerAddressService.addCustomerAddress(customerAddress);
        if(savedCustomerAddress == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(savedCustomerAddress);
    }
    @DeleteMapping("/{id}") //address id
    public ResponseEntity<Void> deleteCustomerAddress(@PathVariable Integer id) {
        boolean deleted = customerAddressService.deleteCustomerAddress(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}

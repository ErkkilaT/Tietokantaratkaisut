package fi.metropolia.teemuerk.webstoreapi.controller;

import fi.metropolia.teemuerk.webstoreapi.dto.CustomerDto;
import fi.metropolia.teemuerk.webstoreapi.entity.Customer;
import fi.metropolia.teemuerk.webstoreapi.service.CustomerService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Integer id){
        CustomerDto customerDto = customerService.getCustomerDto(id);
        if (customerDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customerDto);

    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.addCustomer(customer);
        if(savedCustomer == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(savedCustomer);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id,@RequestBody Customer customer) {

        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        if (updatedCustomer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCustomer);
    }
}

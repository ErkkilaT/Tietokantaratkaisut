package fi.metropolia.teemuerk.webstoreapi.service;

import fi.metropolia.teemuerk.webstoreapi.dto.CustomerDto;
import fi.metropolia.teemuerk.webstoreapi.entity.Customer;
import fi.metropolia.teemuerk.webstoreapi.mapper.CustomerMapper;
import fi.metropolia.teemuerk.webstoreapi.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }



    public CustomerDto getCustomerDto(Integer id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            return null;
        }
        return customerMapper.toDto(customer);
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Integer id, Customer patch) {

        Optional<Customer> optional = customerRepository.findById(id);

        if (optional.isEmpty()) {
            return null;
        }

        Customer customer = optional.get();

        if (patch.getFirstName() != null) {
            customer.setFirstName(patch.getFirstName());
        }

        if (patch.getLastName() != null) {
            customer.setLastName(patch.getLastName());
        }

        if (patch.getEmail() != null) {
            customer.setEmail(patch.getEmail());
        }

        if (patch.getPhone() != null) {
            customer.setPhone(patch.getPhone());
        }

        return customerRepository.save(customer);
    }
}

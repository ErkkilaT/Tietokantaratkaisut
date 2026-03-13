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

    public CustomerDto addCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.toEntity(customerDto);
        Customer saved = customerRepository.save(customer);
        return customerMapper.toDto(saved);
    }

    public boolean deleteCustomer(Integer id) {
        if (!customerRepository.existsById(id)) {
            return false;
        }
        customerRepository.deleteById(id);
        return true;
    }

    public CustomerDto updateCustomer(Integer id, CustomerDto patchDto) {

        Optional<Customer> optional = customerRepository.findById(id);

        if (optional.isEmpty()) {
            return null;
        }

        Customer customer = optional.get();

        if (patchDto.getFirstName() != null) {
            customer.setFirstName(patchDto.getFirstName());
        }

        if (patchDto.getLastName() != null) {
            customer.setLastName(patchDto.getLastName());
        }

        if (patchDto.getEmail() != null) {
            customer.setEmail(patchDto.getEmail());
        }

        if (patchDto.getPhone() != null) {
            customer.setPhone(patchDto.getPhone());
        }

        Customer saved = customerRepository.save(customer);

        return customerMapper.toDto(saved);
    }
}

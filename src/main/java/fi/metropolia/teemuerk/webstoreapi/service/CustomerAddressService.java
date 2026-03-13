package fi.metropolia.teemuerk.webstoreapi.service;

import fi.metropolia.teemuerk.webstoreapi.entity.Customer;
import fi.metropolia.teemuerk.webstoreapi.entity.CustomerAddress;
import fi.metropolia.teemuerk.webstoreapi.repository.CustomerAddressRepository;
import fi.metropolia.teemuerk.webstoreapi.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerAddressService {
    private final CustomerAddressRepository customerAddressRepository;

    public CustomerAddressService(CustomerAddressRepository customerAddressRepository) {
        this.customerAddressRepository = customerAddressRepository;

    }



    public List<CustomerAddress> getCustomerAddresses(Integer customerId) {
        List<CustomerAddress> addresses = customerAddressRepository.findByCustomerId(customerId);
        if(addresses == null || addresses.isEmpty()) {
            return null;
        }
        return addresses;
    }
    @Transactional
    public CustomerAddress addCustomerAddress(CustomerAddress address) {
        return customerAddressRepository.save(address);
    }

    @Transactional
    public CustomerAddress updateCustomerAddress(Integer id, CustomerAddress updatedAddress) {
        CustomerAddress existingAddress = customerAddressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        // Update only non-null fields (PATCH style)
        if (updatedAddress.getStreet() != null) existingAddress.setStreet(updatedAddress.getStreet());
        if (updatedAddress.getCity() != null) existingAddress.setCity(updatedAddress.getCity());
        if (updatedAddress.getPostal_code() != null) existingAddress.setPostal_code(updatedAddress.getPostal_code());
        if (updatedAddress.getCountry() != null) existingAddress.setCountry(updatedAddress.getCountry());

        return customerAddressRepository.save(existingAddress);

    }

    public boolean deleteCustomerAddress(Integer id) {
        Optional<CustomerAddress> addressOpt = customerAddressRepository.findById(id);
        if (addressOpt.isPresent()) {
            CustomerAddress address = addressOpt.get();
            customerAddressRepository.delete(address);
            return true;
        }
        return false;
    }








}

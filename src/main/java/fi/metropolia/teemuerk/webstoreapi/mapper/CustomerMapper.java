package fi.metropolia.teemuerk.webstoreapi.mapper;

import fi.metropolia.teemuerk.webstoreapi.dto.CustomerAddressDto;
import fi.metropolia.teemuerk.webstoreapi.dto.CustomerDto;
import fi.metropolia.teemuerk.webstoreapi.entity.Customer;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {
    public CustomerDto toDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPhone(customer.getPhone());
        List<CustomerAddressDto> addressDtos = customer.getAddresses().stream()
                .map(addr -> {
                    CustomerAddressDto aDto = new CustomerAddressDto();
                    aDto.setId(addr.getId());
                    aDto.setStreet(addr.getStreet());
                    aDto.setCity(addr.getCity());
                    aDto.setPostalCode(addr.getPostal_code());
                    aDto.setCountry(addr.getCountry());
                    return aDto;
                }).collect(Collectors.toList());

        customerDto.setAddresses(addressDtos);
        return customerDto;
    }
    public Customer toEntity(CustomerDto dto) {
        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        return customer;
    }
}

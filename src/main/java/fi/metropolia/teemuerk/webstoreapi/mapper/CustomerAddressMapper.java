package fi.metropolia.teemuerk.webstoreapi.mapper;

import fi.metropolia.teemuerk.webstoreapi.dto.CustomerAddressDto;
import fi.metropolia.teemuerk.webstoreapi.dto.CustomerDto;
import fi.metropolia.teemuerk.webstoreapi.entity.Customer;
import fi.metropolia.teemuerk.webstoreapi.entity.CustomerAddress;
import org.springframework.stereotype.Component;

@Component
public class CustomerAddressMapper {
    public CustomerAddressDto toDto(CustomerAddress address) {
        CustomerAddressDto dto = new CustomerAddressDto();
        dto.setId(address.getId());
       // dto.setCustomerId(address.getCustomer_id());
        dto.setStreet(address.getStreet());
        dto.setCity(address.getCity());
        dto.setPostalCode(address.getPostal_code());
        dto.setCountry(address.getCountry());
        return dto;
    }

    public CustomerAddress toEntity(CustomerAddressDto dto) {
        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setId(dto.getId());
       // customerAddress.setCustomer_id(dto.getCustomerId());
        customerAddress.setStreet(dto.getStreet());
        customerAddress.setCity(dto.getCity());
        customerAddress.setPostal_code(dto.getPostalCode());
        customerAddress.setCountry(dto.getCountry());
        return customerAddress;
    }


}

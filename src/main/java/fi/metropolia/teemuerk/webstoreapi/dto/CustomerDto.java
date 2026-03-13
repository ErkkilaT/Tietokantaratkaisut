package fi.metropolia.teemuerk.webstoreapi.dto;

import java.util.List;

public class CustomerDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private List<CustomerAddressDto> addresses;

     public CustomerDto() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public List<CustomerAddressDto> getAddresses() {
        return addresses;
    }
    public void setAddresses(List<CustomerAddressDto> addresses) {
        this.addresses = addresses;
    }
}

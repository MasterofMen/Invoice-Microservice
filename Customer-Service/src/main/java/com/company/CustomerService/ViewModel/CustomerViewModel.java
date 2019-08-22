package com.company.CustomerService.ViewModel;

import com.company.CustomerService.dto.Customer;

import java.util.Objects;

public class CustomerViewModel {
    private int customerId;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String zip;
    private String email;
    private String phone;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerViewModel customer = (CustomerViewModel) o;
        return getCustomerId() == customer.getCustomerId() &&
                Objects.equals(getCity(), customer.getCity()) &&
                Objects.equals(getEmail(), customer.getEmail()) &&
                Objects.equals(getFirstName(), customer.getFirstName()) &&
                Objects.equals(getLastName(), customer.getLastName()) &&
                Objects.equals(getPhone(), customer.getPhone()) &&
                Objects.equals(getStreet(), customer.getStreet()) &&
                Objects.equals(getZip(), customer.getZip());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerId(), getCity(), getEmail(), getFirstName(), getLastName(), getPhone(), getStreet(), getZip());
    }
}

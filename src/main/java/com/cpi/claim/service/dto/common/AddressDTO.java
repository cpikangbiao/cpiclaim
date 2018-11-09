package com.cpi.claim.service.dto.common;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Address entity.
 */
public class AddressDTO implements Serializable {

    private Long id;

    private String name;

    private String building;

    private String street;

    private String postbox;

    private String zip;

    private String city;

    private Long companyId;

    private String companyCompanyName;

    private Long addressTypeId;

    private String addressTypeAddressTypeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostbox() {
        return postbox;
    }

    public void setPostbox(String postbox) {
        this.postbox = postbox;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyCompanyName() {
        return companyCompanyName;
    }

    public void setCompanyCompanyName(String companyCompanyName) {
        this.companyCompanyName = companyCompanyName;
    }

    public Long getAddressTypeId() {
        return addressTypeId;
    }

    public void setAddressTypeId(Long addressTypeId) {
        this.addressTypeId = addressTypeId;
    }

    public String getAddressTypeAddressTypeName() {
        return addressTypeAddressTypeName;
    }

    public void setAddressTypeAddressTypeName(String addressTypeAddressTypeName) {
        this.addressTypeAddressTypeName = addressTypeAddressTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AddressDTO addressDTO = (AddressDTO) o;
        if (addressDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), addressDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AddressDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", building='" + getBuilding() + "'" +
            ", street='" + getStreet() + "'" +
            ", postbox='" + getPostbox() + "'" +
            ", zip='" + getZip() + "'" +
            ", city='" + getCity() + "'" +
            ", company=" + getCompanyId() +
            ", company='" + getCompanyCompanyName() + "'" +
            ", addressType=" + getAddressTypeId() +
            ", addressType='" + getAddressTypeAddressTypeName() + "'" +
            "}";
    }
}

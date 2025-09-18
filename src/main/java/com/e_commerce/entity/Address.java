package com.e_commerce.entity;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="address")
@Data
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long addressId;
 private String street;
 private String city;
 private String state;
 private String country;
 private String postalcode;
 
 @ManyToOne
 @JoinColumn(name="userId",referencedColumnName = "userId")
 @JsonBackReference("user-address")
 private User user;

 @Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Address)) return false;
    Address address = (Address) o;
    return addressId != null && addressId.equals(address.addressId);
}

@Override
public int hashCode() {
    return getClass().hashCode(); 
}
	
}

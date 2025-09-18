package com.e_commerce.entity;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Table(name="user")
@Data
public class User  implements UserDetails{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long userId;
@NonNull
private String first_name;
private String last_name;
@Column(unique = true)
@NonNull
@Email(message="Provide email")
private String email;
@Column(unique = true)
@NonNull
@Pattern(regexp = "\\d{10}", message = "Mobile number must be exactly 10 digits")
private String mobile_no;
@Column(unique = true)
private String username;
private String password;
private String Gender;
private Date dob;

@OneToMany(mappedBy = "user",cascade = {jakarta.persistence.CascadeType.PERSIST,jakarta.persistence.CascadeType.MERGE})
@JsonManagedReference("user-address")
private List<Address>listAddresses;

@OneToMany(mappedBy = "user",cascade = {jakarta.persistence.CascadeType.PERSIST,jakarta.persistence.CascadeType.MERGE})
@JsonManagedReference("user-roles")
private Set<Role>roles;

@OneToOne(mappedBy = "user",cascade = {jakarta.persistence.CascadeType.PERSIST,jakarta.persistence.CascadeType.MERGE})
@JsonManagedReference("user-cart")
private Cart cart;

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User)) return false;
    User user = (User) o;
    return userId != null && userId.equals(user.userId);
}

@Override
public int hashCode() {
    return getClass().hashCode(); }

@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles.stream()
            .map(role -> new SimpleGrantedAuthority(role.getRole()))
            .toList();
}




}

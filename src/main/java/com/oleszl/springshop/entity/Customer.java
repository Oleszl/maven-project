package com.oleszl.springshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
@Entity
public class Customer extends BaseEntity{
    private String firstName;
    private String lastName;
    private String email;
    @OneToMany(mappedBy = "customer", orphanRemoval = true)
    private Set<Order> orders = new HashSet<>();
}

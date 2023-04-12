package com.oleszl.springshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
@Entity
@ToString(exclude = "orders")
public class Product extends BaseEntity{
    private String name;
    @Column(columnDefinition = "Decimal(7,2)")
    private Double price;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            },
            mappedBy = "products")
    @JsonIgnore
    private Set<Order> orders;
}

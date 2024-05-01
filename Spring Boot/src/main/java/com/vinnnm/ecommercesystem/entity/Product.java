package com.vinnnm.ecommercesystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private long quantity;

    @Column(name = "price")
    private double price;

    @Column(name = "color")
    private String color;

    @Column(name = "created_at")
    private long createdAt;

    @ManyToMany(mappedBy = "products")
    private Set<Category> categories;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
}
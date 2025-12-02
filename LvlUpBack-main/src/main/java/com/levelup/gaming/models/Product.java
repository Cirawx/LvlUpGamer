package com.levelup.gaming.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private String id;
    private String name;
    @Column(length = 1000)
    private String description;
    private double price;
    @Column(columnDefinition = "LONGTEXT")
    private String imageUrl;
    private double rating;
    private int numReviews;
    private boolean isTopSelling;
    private int countInStock;
    private String category;
    @Column(length = 1000)
    private String specifications; // Storing JSON string as is for simplicity
    private boolean isActive;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private List<Review> reviews = new ArrayList<>();
}
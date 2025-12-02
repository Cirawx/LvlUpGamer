package com.levelup.gaming.models;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String type;
    private String name;
    private int pointsCost;
    private String description;
    @JsonProperty("isActive")
    private boolean isActive;
    private String season;
    private String imageUrl;

    private String discountType;
    private Double discountValue;

    private Integer stock;
    private Integer stockAvailable;
}

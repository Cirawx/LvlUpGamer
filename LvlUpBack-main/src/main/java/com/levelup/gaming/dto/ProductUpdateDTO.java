package com.levelup.gaming.dto;

import lombok.Data;

@Data
public class ProductUpdateDTO {
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private String category;
    private Integer countInStock;
    private String specifications;
    private Boolean active;
}

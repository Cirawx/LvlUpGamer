package com.levelup.gaming.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RewardUpdateDTO {
    private String name;
    private String type;
    private Integer pointsCost;
    private String description;
    @JsonProperty("isActive")
    private Boolean isActive;
    private String season;
    private String imageUrl;
    private String discountType;
    private Double discountValue;
    private Integer stock;
    private Integer stockAvailable;
}

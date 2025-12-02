package com.levelup.gaming.dto;

import lombok.Data;

@Data
public class ReviewCreateDTO {
    private String name;
    private int rating;
    private String comment;
}

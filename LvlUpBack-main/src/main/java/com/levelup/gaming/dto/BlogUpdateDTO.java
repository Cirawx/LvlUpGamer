package com.levelup.gaming.dto;

import lombok.Data;

@Data
public class BlogUpdateDTO {
    private String title;
    private String excerpt;
    private String content;
    private String imageUrl;
    private String author;
}

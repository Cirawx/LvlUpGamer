package com.levelup.gaming.dto;

import lombok.Data;

@Data
public class VideoUpdateDTO {
    private String title;
    private String embedUrl;
    private Boolean isFeatured;
}

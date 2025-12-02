package com.levelup.gaming.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video {
    @Id
    private String id;
    private String title;
    @Column(length = 1000)
    private String embedUrl;
    @JsonProperty("isFeatured")
    private boolean isFeatured;
}

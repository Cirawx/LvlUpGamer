package com.levelup.gaming.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    private String id;
    private String title;
    private LocalDate date;
    private LocalTime time;
    private String location;
    @Column(length = 1000)
    private String mapEmbed;
    private String mapUrl;
    private String notes;
}

package com.levelup.gaming.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class EventUpdateDTO {
    private String title;
    private LocalDate date;
    private LocalTime time;
    private String location;
    private String mapEmbed;
    private String mapUrl;
    private String notes;
}

package com.levelup.gaming.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String rut;
    private int age;
    private String role;
    private boolean hasDuocDiscount;
    private int points;
    private String referralCode;
    @com.fasterxml.jackson.annotation.JsonProperty("isActive")
    private boolean isActive;

    @Transient
    private String token;

    @Embedded
    private Address address;

    private java.util.Date lastLogout;
}

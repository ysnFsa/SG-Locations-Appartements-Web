package com.SGlocationappartements.rent.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Use Long to ensure compatibility

    private String nom;
    private String prenom;
    private String username;
    private String passwd;
    private String email;
    private LocalDate birth_date;
    private LocalDateTime last_login;
    private LocalDateTime register_date;
    private String avatar_url;
    private LocalDateTime last_seen;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Other getters and setters
}

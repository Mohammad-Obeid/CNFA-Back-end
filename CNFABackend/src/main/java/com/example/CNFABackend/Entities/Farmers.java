package com.example.CNFABackend.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.Instant;

@Data
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "farmer")
public class Farmers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    private String address;
    private String phone;
    private String nationalId;
    private String description;
    private boolean is_locked = false;
    private Timestamp regDate=Timestamp.from(Instant.now());
    private Timestamp lastModified;


}

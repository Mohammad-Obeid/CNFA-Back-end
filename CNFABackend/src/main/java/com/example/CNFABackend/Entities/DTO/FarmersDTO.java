package com.example.CNFABackend.Entities.DTO;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class FarmersDTO {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String nationalId;
    private String description;
    private boolean is_locked = false;
    private Timestamp regDate;
}

package com.example.CNFABackend.Entities;

import lombok.Getter;
import lombok.Setter;

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
}

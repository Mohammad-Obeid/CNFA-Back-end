package com.example.CNFABackend.Entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class UserDTO {
    private String username;
    private String email;
    private String Rule="Employee";
}

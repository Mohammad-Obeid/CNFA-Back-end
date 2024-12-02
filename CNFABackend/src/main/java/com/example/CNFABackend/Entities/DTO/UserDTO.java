package com.example.CNFABackend.Entities.DTO;

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

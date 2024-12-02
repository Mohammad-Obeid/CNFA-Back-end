package com.example.CNFABackend.Entities.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Data
@Setter
@Getter
public class LoggerDTO {
    private String userName;
    private String operation;
    private String farmerName;
    private Timestamp opTime;
}


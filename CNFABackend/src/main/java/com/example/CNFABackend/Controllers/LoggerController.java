package com.example.CNFABackend.Controllers;

import com.example.CNFABackend.Entities.DTO.LoggerDTO;
import com.example.CNFABackend.Services.LoggerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("logger")
public class LoggerController {
    private final LoggerService logService;

    public LoggerController(LoggerService logService) {
        this.logService = logService;
    }

    @GetMapping("/{pageNum}")
    public ResponseEntity<List<LoggerDTO>> getAllLogs(@PathVariable int pageNum) {
        Optional<List<LoggerDTO>> logs= Optional.ofNullable(logService.getAllLogs(pageNum));
        return logs.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null));
    }
    @GetMapping("/page")
    public int getNumOfPages(
    ){
        int num= logService.getNumOfPagesForEmployyes();
        return num;
    }
    @PostMapping("")
    public ResponseEntity<LoggerDTO> AddNewLog(@RequestBody LoggerDTO loggerDTO) {
        Optional<LoggerDTO> log = Optional.ofNullable(logService.AddNewLog(loggerDTO));
        return log.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null));
    }

}

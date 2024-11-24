package com.example.CNFABackend.Services;

import com.example.CNFABackend.Entities.Logger;
import com.example.CNFABackend.Entities.LoggerDTO;
import com.example.CNFABackend.Entities.User;
import com.example.CNFABackend.Entities.UserDTO;
import com.example.CNFABackend.Reposititories.LoggerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoggerService {
    private final LoggerRepository logRepo;

    public LoggerService(LoggerRepository logRepo) {
        this.logRepo = logRepo;
    }


    private LoggerDTO MapToDTO(Logger logger){
        LoggerDTO loggerD = new LoggerDTO();
        loggerD.setUserName(logger.getUserName());
        loggerD.setOperation(logger.getOperation());
        loggerD.setFarmerName(logger.getFarmerName());
        loggerD.setOpTime(logger.getOpTime());
        return loggerD;
    }

    private Logger MapToLogger(LoggerDTO loggerD){
        Logger logger = new Logger();
        logger.setUserName(loggerD.getUserName());
        logger.setOperation(loggerD.getOperation());
        logger.setFarmerName(loggerD.getFarmerName());
        logger.setOpTime(loggerD.getOpTime());
        return logger;
    }

    public List<LoggerDTO> getAllLogs(int pageNum) {
        Page<Logger> logs = logRepo.findAll(PageRequest.of(pageNum, 10, Sort.by(Sort.Order.desc("opTime"))));
        return logs.getContent().stream().map(this::MapToDTO).collect(Collectors.toList());
    }

    public int getNumOfPagesForEmployyes() {
        long totalProducts = logRepo.count();
        return (int) Math.ceil((double) totalProducts / 10);
    }

    public LoggerDTO AddNewLog(LoggerDTO loggerDTO) {
        Logger logger = MapToLogger(loggerDTO);
        logger = logRepo.save(logger);
        return loggerDTO;
    }
}

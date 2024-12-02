package com.example.CNFABackend.Controllers;

import com.example.CNFABackend.Entities.DTO.FarmersDTO;
import com.example.CNFABackend.Services.FarmerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("farmer")
public class FarmerController {

    private static FarmerService farmerSer;

    public FarmerController(FarmerService farmerService) {
        this.farmerSer = farmerService;
    }


    @GetMapping("/page/{pagenum}")
    public ResponseEntity<List<FarmersDTO>> getFarmer(@PathVariable int pagenum) {

        Optional<List<FarmersDTO>> farmers= Optional.ofNullable(farmerSer.getAllFarmers(pagenum));
        return farmers.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null));
    }
    @GetMapping("/page")
    public int getNumOfPages(
    ){
        int num= farmerSer.getNumOfPages();
        return num;
    }


    @GetMapping("/{id}")
    public ResponseEntity<FarmersDTO> getFarmerById(@PathVariable("id") String id) {

        Optional<FarmersDTO> farmer= Optional.ofNullable(farmerSer.getFarmer(id));
        return farmer.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null));
    }
    @PostMapping("")
    public ResponseEntity<FarmersDTO> CreateFarmer(@RequestBody FarmersDTO farmerDTO) {

        Optional<FarmersDTO> farmer= Optional.ofNullable(farmerSer.CreateFarmer(farmerDTO));
        return farmer.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.FOUND)
                        .body(null));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FarmersDTO> UpdateFarmer(@PathVariable("id") String id, @RequestBody FarmersDTO farmerDTO) {
        Optional<FarmersDTO> farmer= Optional.ofNullable(farmerSer.UpdateFarmer(id, farmerDTO));
        return farmer.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> DeleteFarmer(@PathVariable("id") String id) {
        Optional<Boolean> farmer= Optional.ofNullable(farmerSer.DeleteFarmer(id));
        return farmer.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(false));
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<List<FarmersDTO>> getFarmerStartingWithId(@PathVariable("id") String id) {

        Optional<List<FarmersDTO>> farmer= Optional.ofNullable(farmerSer.getFarmerStartingWithId(id));
        return farmer.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null));
    }


    @PatchMapping("/lock/{id}")
    public ResponseEntity<FarmersDTO> LockFarmer(@PathVariable("id") String id) {
        Optional<FarmersDTO> farmer= Optional.ofNullable(farmerSer.lockFarmer(id));
        return farmer.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null));
    }

    @PatchMapping("/unlock/{id}")
    public ResponseEntity<FarmersDTO> UnLockFarmer(@PathVariable("id") String id) {
        Optional<FarmersDTO> farmer= Optional.ofNullable(farmerSer.UnLockFarmer(id));
        return farmer.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null));
    }

}

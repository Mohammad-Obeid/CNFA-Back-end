package com.example.CNFABackend.Services;

import com.example.CNFABackend.Entities.Farmers;
import com.example.CNFABackend.Entities.DTO.FarmersDTO;
import com.example.CNFABackend.Reposititories.FarmerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FarmerService {
    private static FarmerRepository farmerRepo;
    public FarmerService(FarmerRepository farmerRepository) {
        this.farmerRepo = farmerRepository;
    }
    private FarmersDTO MapToDTO(Farmers farmer){
        FarmersDTO farmerD = new FarmersDTO();
        farmerD.setId(farmer.getId());
        farmerD.setName(farmer.getName());
        farmerD.setAddress(farmer.getAddress());
        farmerD.setPhone(farmer.getPhone());
        farmerD.setNationalId(farmer.getNationalId());
        farmerD.setDescription(farmer.getDescription());
        farmerD.set_locked(farmer.is_locked());
        return farmerD;
    }

    private Farmers MapToFarmer(FarmersDTO farmerD){
        Farmers farmer = new Farmers();
        farmer.setId(farmerD.getId());
        farmer.setName(farmerD.getName());
        farmer.setAddress(farmerD.getAddress());
        farmer.setPhone(farmerD.getPhone());
        farmer.setNationalId(farmerD.getNationalId());
        farmer.setDescription(farmerD.getDescription());
        farmer.set_locked(farmerD.is_locked());
        return farmer;
    }

    public List<FarmersDTO> getAllFarmers(int pagenum) {
        PageRequest pageRequest = PageRequest.of(pagenum, 10); // Correct creation of PageRequest
        Page<Farmers> farmersPage = farmerRepo.findAll(pageRequest);

        // Return the list of mapped FarmersDTO or an empty list if no farmers are found
        return farmersPage.hasContent() ? farmersPage.getContent().stream()
                .map(this::MapToDTO)
                .collect(Collectors.toList()) : Collections.emptyList();
    }

    public int getNumOfPages() {
        long totalProducts = farmerRepo.count();
        return (int) Math.ceil((double) totalProducts / 10);
    }


    public FarmersDTO getFarmer(String id) {
        Optional<Farmers> farmer = farmerRepo.findFarmersByNationalId(id);
        return farmer.isEmpty()? null : MapToDTO(farmer.get());
    }

    public FarmersDTO CreateFarmer(FarmersDTO farmerDTO) {
        Optional<Farmers> farmer =  farmerRepo.findFarmersByNationalId(farmerDTO.getNationalId());
        if(farmer.isPresent()) return null;
        Farmers farmers = MapToFarmer(farmerDTO);
        farmerRepo.save(farmers);
        return MapToDTO(farmers);
    }

    public FarmersDTO UpdateFarmer(String id, FarmersDTO farmerDTO) {
        Optional<Farmers> farmer =  farmerRepo.findFarmersByNationalId(id);
        if(farmer.isEmpty()) return null;
        farmer.get().setName(farmerDTO.getName());
        farmer.get().setAddress(farmerDTO.getAddress());
        farmer.get().setPhone(farmerDTO.getPhone());
        farmer.get().setNationalId(farmerDTO.getNationalId());
        farmer.get().setDescription(farmerDTO.getDescription());
        farmerRepo.save(farmer.get());
        return MapToDTO(farmer.get());
    }

    public Boolean DeleteFarmer(String id) {
        Optional<Farmers> farmer =  farmerRepo.findFarmersByNationalId(id);
        if(farmer.isEmpty()) return false;
        farmerRepo.delete(farmer.get());
        return true;
    }

    public List<FarmersDTO> getFarmerStartingWithId(String id) {
        Optional<List<Farmers>> farmers = farmerRepo.findTop10FarmersByNationalIdStartingWith(id);
        return farmers.orElse(Collections.emptyList()) // Use an empty list if not present
                .stream() // Stream over the list, not the Optional
                .map(this::MapToDTO)
                .collect(Collectors.toList());
    }


    public FarmersDTO lockFarmer(String id) {
        Optional<Farmers> farmer = farmerRepo.findFarmersByNationalId(id);
        if(farmer.isEmpty())return null;
        farmer.get().set_locked(true);
        farmerRepo.save(farmer.get());
        return MapToDTO(farmer.get());
    }

    public FarmersDTO UnLockFarmer(String id) {
        Optional<Farmers> farmer = farmerRepo.findFarmersByNationalId(id);
        if(farmer.isEmpty())return null;
        farmer.get().set_locked(false);
        farmerRepo.save(farmer.get());
        return MapToDTO(farmer.get());
    }
}

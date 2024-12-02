package com.example.CNFABackend.Services;

import com.example.CNFABackend.Entities.Farmers;
import com.example.CNFABackend.Entities.ImageData;
import com.example.CNFABackend.Reposititories.FarmerRepository;
import com.example.CNFABackend.Reposititories.StorageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

@Service
public class StorageService {
    private final StorageRepository repository;
    private final FarmerRepository farmerRepository;

    public StorageService(StorageRepository repository, FarmerRepository farmerRepo, FarmerRepository farmerRepository) {
        this.repository = repository;
        this.farmerRepository = farmerRepository;
    }

    public ImageData uploadImage(MultipartFile file, String NationalID) throws IOException {
        Optional<Farmers> farmer = farmerRepository.findFarmersByNationalId(NationalID);
        if(farmer.isPresent()) {
            Optional<ImageData> img = repository.findByNationalId(NationalID);
            if (img.isPresent()) {
                byte[] bytes = file.getBytes();
                String base64Encoded = Base64.getEncoder().encodeToString(bytes);
                img.get().setBase64(base64Encoded);
                repository.save(img.get());
                return img.get();
            }
            ImageData imageData = new ImageData();
            imageData.setNationalId(NationalID);
            imageData.setContentType(file.getContentType());
            byte[] bytes = file.getBytes();
            String base64Encoded = Base64.getEncoder().encodeToString(bytes);
            imageData.setBase64(base64Encoded);
            repository.save(imageData);
            return imageData;
        }
        return null;

    }

    public String downloadImage(String nationalID) {
        Optional<Farmers> farmer = farmerRepository.findFarmersByNationalId(nationalID);
        if(farmer.isPresent()) {
        String base64Images = "";
        Optional<ImageData> image = repository.findByNationalId(nationalID);
        if(image.isPresent()){
        base64Images+="data:"+image.get().getContentType()+";base64,"+((image.get().getBase64()));
        return base64Images;}
        }
        return null;
    }


    public Boolean deleteImage(String nationalId) {
            Optional<ImageData> img = repository.findByNationalId(nationalId);
            if (img.isPresent()) {
                repository.delete(img.get());
                return true;
            }
            return false;
        }
}

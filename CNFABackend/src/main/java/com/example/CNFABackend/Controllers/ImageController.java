package com.example.CNFABackend.Controllers;

import com.example.CNFABackend.Entities.ImageData;
import com.example.CNFABackend.Services.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/image")
public class ImageController {

    private final StorageService service;

    public ImageController(StorageService service) {
        this.service = service;
    }

    @PostMapping("/{nationalId}")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file
            , @PathVariable("nationalId") String nationalId) throws IOException {
        Optional<ImageData> uploadImage = Optional.of(service.uploadImage(file,nationalId ));
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<String> getIdImage(@PathVariable String fileName) {
        String base64Images = service.downloadImage(fileName);
        return new ResponseEntity<>(base64Images, HttpStatus.OK);
    }
    @DeleteMapping("/{nationalId}")
    public ResponseEntity<Boolean> deleteImage(@PathVariable String nationalId) {
        Optional<Boolean> deleteImage = Optional.of(service.deleteImage(nationalId ));
        return deleteImage.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null));
    }
}

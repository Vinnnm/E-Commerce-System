package com.vinnnm.ecommercesystem.controllers;

import com.vinnnm.ecommercesystem.dtos.BrandDTO;
import com.vinnnm.ecommercesystem.dtos.CategoryDTO;
import com.vinnnm.ecommercesystem.dtos.ProductDTO;
import com.vinnnm.ecommercesystem.service.BrandService;
import com.vinnnm.ecommercesystem.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/brand")
public class BrandController {
    @Autowired
    private final BrandService brandService;

    @PostMapping("/create")
    public ResponseEntity<String> createBrand(@RequestBody BrandDTO brandDTO) {
        if (brandService.addNewBrand(brandDTO)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Brand created successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create brand.");
        }
    }

    @GetMapping("/getAllBrand")
    public ResponseEntity<?> getAllBrand() {
        List<BrandDTO> brandDTOS = brandService.getAllBrand();
        if (brandDTOS != null && !brandDTOS.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(brandDTOS);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("No brands found.");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteBrand(@RequestParam ("id") long id) {
        if (brandService.delete(id)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Brand deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete brand.");
        }
    }
}

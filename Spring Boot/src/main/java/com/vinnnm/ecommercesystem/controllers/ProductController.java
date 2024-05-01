package com.vinnnm.ecommercesystem.controllers;

import com.vinnnm.ecommercesystem.dtos.ProductDTO;
import com.vinnnm.ecommercesystem.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@RequestBody ProductDTO productDTO) {
        if (productService.addNewProduct(productDTO)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Product created successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create product.");
        }
    }

    @GetMapping("/getAllProduct")
    public ResponseEntity<?> getAllProduct() {
        Set<ProductDTO> productDTOS = productService.getAllProducts();
        if (productDTOS != null && !productDTOS.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(productDTOS);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("No products found.");
        }
    }

    @GetMapping ("/findProduct")
    public ResponseEntity<?> findProduct(@RequestParam ("id") long id) {
        ProductDTO productDTO = productService.getProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body(Objects.requireNonNullElse(productDTO, "No products found."));
    }

    @DeleteMapping ("/delete")
    public ResponseEntity<?> deleteProduct(@RequestParam ("id") long id) {
        if (productService.delete(id)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Product deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete product.");
        }
    }

    @PostMapping ("/buy")
    public ResponseEntity<?> buyProduct(@RequestParam ("id") long id, @RequestParam ("quantity") long quantity) {
        if (productService.buyProduct(id, quantity)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Product bought successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to buy product.");
        }
    }

    @GetMapping ("/findByBrand")
    public ResponseEntity<?> findByBrand(@RequestParam ("brandId") long brandId) {
        List<ProductDTO> productDTOs = productService.getProductsByBrand(brandId);
        return ResponseEntity.status(HttpStatus.OK).body(Objects.requireNonNullElse(productDTOs, "No products found."));
    }
}

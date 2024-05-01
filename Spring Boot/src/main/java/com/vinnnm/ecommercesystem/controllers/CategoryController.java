package com.vinnnm.ecommercesystem.controllers;

import com.vinnnm.ecommercesystem.dtos.BrandDTO;
import com.vinnnm.ecommercesystem.dtos.CategoryDTO;
import com.vinnnm.ecommercesystem.dtos.ProductDTO;
import com.vinnnm.ecommercesystem.service.CategoryService;
import com.vinnnm.ecommercesystem.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<String> createCategory(@RequestBody CategoryDTO categoryDTO) {
        if (categoryService.addNewCategory(categoryDTO)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Category created successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create category.");
        }
    }

    @GetMapping("/getAllCategory")
    public ResponseEntity<?> getAllCategory() {
        List<CategoryDTO> categoryDTOS = categoryService.getAllCategory();
        if (categoryDTOS != null && !categoryDTOS.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(categoryDTOS);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("No categories found.");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCategory(@RequestParam ("id") long id) {
        if (categoryService.delete(id)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Category deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete category.");
        }
    }
}

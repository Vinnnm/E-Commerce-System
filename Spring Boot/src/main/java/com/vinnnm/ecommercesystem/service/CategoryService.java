package com.vinnnm.ecommercesystem.service;

import com.vinnnm.ecommercesystem.dtos.CategoryDTO;
import com.vinnnm.ecommercesystem.entity.Category;
import com.vinnnm.ecommercesystem.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface CategoryService {
    boolean addNewCategory(CategoryDTO categoryDTO);
    List<CategoryDTO> getAllCategory();
    boolean delete(long id);

    List<Category> getCategoriesByProduct(Product product);
}

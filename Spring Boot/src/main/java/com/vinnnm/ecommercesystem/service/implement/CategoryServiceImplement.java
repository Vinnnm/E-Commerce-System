package com.vinnnm.ecommercesystem.service.implement;

import com.vinnnm.ecommercesystem.daos.CategoryRepository;
import com.vinnnm.ecommercesystem.dtos.CategoryDTO;
import com.vinnnm.ecommercesystem.dtos.ProductDTO;
import com.vinnnm.ecommercesystem.entity.Category;
import com.vinnnm.ecommercesystem.entity.Product;
import com.vinnnm.ecommercesystem.service.CategoryService;
import com.vinnnm.ecommercesystem.util.Helper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class CategoryServiceImplement implements CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    @Autowired
    private final ModelMapper modelMapper;
    @Override
    public boolean addNewCategory(CategoryDTO categoryDTO) {
        try {
            categoryDTO.setCreatedAt(Helper.getCurrentTimestamp());
            categoryRepository.save(modelMapper.map(categoryDTO, Category.class));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        if(categories.isEmpty()) {
            return null;
        } else {
            for (Category category : categories) {
                CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
                categoryDTO.setCreatedAtDate(Helper.convertTimestampToDate(category.getCreatedAt()));
                categoryDTOS.add(categoryDTO);
            }
        }
        return categoryDTOS;
    }

    @Override
    public boolean delete(long id) {
        if (categoryRepository.existsById(id)) {
            try {
                categoryRepository.deleteById(id);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public List<Category> getCategoriesByProduct(Product product) {
        return categoryRepository.findByProducts(product);
    }
}

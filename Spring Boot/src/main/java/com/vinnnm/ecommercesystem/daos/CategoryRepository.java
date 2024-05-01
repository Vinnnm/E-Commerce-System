package com.vinnnm.ecommercesystem.daos;

import com.vinnnm.ecommercesystem.entity.Category;
import com.vinnnm.ecommercesystem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByProducts(Product product);
}

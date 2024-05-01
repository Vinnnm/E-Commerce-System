package com.vinnnm.ecommercesystem.daos;

import com.vinnnm.ecommercesystem.entity.Category;
import com.vinnnm.ecommercesystem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategories(Category category);
    List<Product> findByBrandId(long brandId);
}

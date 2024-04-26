package com.vinnnm.ecommercesystem.daos;

import com.vinnnm.ecommercesystem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

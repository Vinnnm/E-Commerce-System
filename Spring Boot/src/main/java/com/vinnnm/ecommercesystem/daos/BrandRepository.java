package com.vinnnm.ecommercesystem.daos;

import com.vinnnm.ecommercesystem.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    Brand findByName(String name);
}

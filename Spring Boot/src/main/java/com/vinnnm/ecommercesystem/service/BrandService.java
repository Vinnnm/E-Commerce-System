package com.vinnnm.ecommercesystem.service;

import com.vinnnm.ecommercesystem.dtos.BrandDTO;
import com.vinnnm.ecommercesystem.entity.Brand;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandService {
    boolean addNewBrand(BrandDTO brandDTO);
    List<BrandDTO> getAllBrand();
    boolean delete(long id);
    BrandDTO getByName(String name);
    boolean addNewBrandByName(String name);
    boolean isBrandExists(String name);
}

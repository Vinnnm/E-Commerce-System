package com.vinnnm.ecommercesystem.service;

import com.vinnnm.ecommercesystem.dtos.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface ProductService {
    boolean addNewProduct(ProductDTO productDTO);
    Set<ProductDTO> getAllProducts();
    ProductDTO getProductById(long id);
    boolean delete(long id);
    boolean buyProduct(long id, long quantity);

    List<ProductDTO> getProductsByBrand(long brandId);
}

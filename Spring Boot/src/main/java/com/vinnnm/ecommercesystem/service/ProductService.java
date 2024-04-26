package com.vinnnm.ecommercesystem.service;

import com.vinnnm.ecommercesystem.dtos.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {
    boolean addNewProduct(ProductDTO productDTO);
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(long id);
    boolean delete(long id);
    boolean buyProduct(long id);
}

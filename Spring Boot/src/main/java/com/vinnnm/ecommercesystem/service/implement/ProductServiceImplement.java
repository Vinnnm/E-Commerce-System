package com.vinnnm.ecommercesystem.service.implement;

import com.vinnnm.ecommercesystem.daos.ProductRepository;
import com.vinnnm.ecommercesystem.dtos.ProductDTO;
import com.vinnnm.ecommercesystem.entity.Product;
import com.vinnnm.ecommercesystem.service.ProductService;
import com.vinnnm.ecommercesystem.util.Helper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class ProductServiceImplement implements ProductService {

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public boolean addNewProduct(ProductDTO productDTO) {
        try {
            productDTO.setCreatedAt(Helper.getCurrentTimestamp());
            productRepository.save(modelMapper.map(productDTO, Product.class));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();
        if(products.isEmpty()) {
            return null;
        } else {
            for (Product product : products) {
                ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
                productDTO.setCreatedAtDate(Helper.convertTimestampToDate(product.getCreatedAt()));
                productDTOS.add(productDTO);
            }
        }
        return productDTOS;
    }

    @Override
    public ProductDTO getProductById(long id) {
        return modelMapper.map(productRepository.findById(id), ProductDTO.class);
    }

    @Override
    public boolean delete(long id) {
        if (productRepository.existsById(id)) {
            try {
                productRepository.deleteById(id);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }
}

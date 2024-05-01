package com.vinnnm.ecommercesystem.service.implement;

import com.vinnnm.ecommercesystem.daos.ProductRepository;
import com.vinnnm.ecommercesystem.dtos.ProductDTO;
import com.vinnnm.ecommercesystem.entity.Brand;
import com.vinnnm.ecommercesystem.entity.Category;
import com.vinnnm.ecommercesystem.entity.Product;
import com.vinnnm.ecommercesystem.service.BrandService;
import com.vinnnm.ecommercesystem.service.CategoryService;
import com.vinnnm.ecommercesystem.service.ProductService;
import com.vinnnm.ecommercesystem.util.Helper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class ProductServiceImplement implements ProductService {

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final BrandService brandService;

    @Autowired
    private final CategoryService categoryService;

    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public boolean addNewProduct(ProductDTO productDTO) {
        try {
            productDTO.setCreatedAt(Helper.getCurrentTimestamp());
            if(!brandService.addNewBrandByName(productDTO.getBrandName()))
                throw new RuntimeException("Failed to add new brand");
            productDTO.setBrand(modelMapper.map(brandService.getByName(productDTO.getBrandName()), Brand.class));
            productRepository.save(modelMapper.map(productDTO, Product.class));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Set<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        Set<ProductDTO> productDTOS = new HashSet<>();
        if (products.isEmpty()) {
            return null;
        } else {
            for (Product product : products) {
                ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
                productDTO.setCreatedAtDate(Helper.convertTimestampToDate(product.getCreatedAt()));
                Set<Category> categories = new HashSet<>(categoryService.getCategoriesByProduct(product));
                List<String> categoryNames = categories.stream()
                        .map(Category::getName)
                        .toList();
                productDTO.setCategoryNamesArray(categoryNames.toArray(new String[0]));
                productDTO.setBrand(null);
                productDTO.setCategories(null);
                productDTOS.add(productDTO);
            }
        }
        return productDTOS;
    }


    @Override
    public ProductDTO getProductById(long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
            productDTO.setCreatedAtDate(Helper.convertTimestampToDate(product.getCreatedAt()));
            Set<Category> categories = new HashSet<>(categoryService.getCategoriesByProduct(product));
            List<String> categoryNames = categories.stream()
                    .map(Category::getName)
                    .toList();
            productDTO.setCategoryNamesArray(categoryNames.toArray(new String[0]));
            productDTO.setBrand(null);
            productDTO.setCategories(null);
            return productDTO;
        } else {
            return null;
        }
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

    @Override
    public boolean buyProduct(long id, long quantity) {
        if (productRepository.existsById(id)) {
            try {
                Product product = modelMapper.map(getProductById(id), Product.class);
                long productQuantity = product.getQuantity();
                long calculatedQuantity = productQuantity - quantity;
                if(calculatedQuantity > 0) {
                    product.setQuantity(calculatedQuantity);
                    if(product.getQuantity() > 1) {
                        productRepository.save(product);
                    } else {
                        productRepository.delete(product);
                    }
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public List<ProductDTO> getProductsByBrand(long[] brandIds) {
        List<Product> products = productRepository.findAllByBrandIdIn(brandIds);
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product : products) {
            ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
            productDTO.setCreatedAtDate(Helper.convertTimestampToDate(product.getCreatedAt()));
            Set<Category> categories = new HashSet<>(categoryService.getCategoriesByProduct(product));
            List<String> categoryNames = categories.stream()
                    .map(Category::getName)
                    .toList();
            productDTO.setCategoryNamesArray(categoryNames.toArray(new String[0]));
            productDTO.setBrand(null);
            productDTO.setCategories(null);
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    @Override
    public List<ProductDTO> getProductsByCategory(long[] categoryIds) {
        List<Product> products = productRepository.findByCategoriesIdIn(categoryIds);
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product : products) {
            ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
            productDTO.setCreatedAtDate(Helper.convertTimestampToDate(product.getCreatedAt()));
            Set<Category> categories = new HashSet<>(categoryService.getCategoriesByProduct(product));
            List<String> categoryNames = categories.stream()
                    .map(Category::getName)
                    .toList();
            productDTO.setCategoryNamesArray(categoryNames.toArray(new String[0]));
            productDTO.setBrand(null);
            productDTO.setCategories(null);
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }
}

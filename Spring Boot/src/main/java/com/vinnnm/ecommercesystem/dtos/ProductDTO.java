package com.vinnnm.ecommercesystem.dtos;

import com.vinnnm.ecommercesystem.entity.Brand;
import com.vinnnm.ecommercesystem.entity.Category;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class ProductDTO {
    private long id, quantity, createdAt, brandId;
    private String name, color, brandName;
    private double price;
    private Date createdAtDate;
    private List<CategoryDTO> categories;
    private Brand brand;
}

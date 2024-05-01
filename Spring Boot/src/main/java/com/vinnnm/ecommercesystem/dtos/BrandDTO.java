package com.vinnnm.ecommercesystem.dtos;

import com.vinnnm.ecommercesystem.entity.Product;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class BrandDTO {
    private long id, createdAt;
    private String name;
    private List<ProductDTO> products;
    private Date createdAtDate;
}

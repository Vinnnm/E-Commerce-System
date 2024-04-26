package com.vinnnm.ecommercesystem.dtos;

import lombok.Data;

import java.util.Date;
@Data
public class ProductDTO {
    private long id, quantity, createdAt;
    private String name, color;
    private double price;
    private Date createdAtDate;
}

package com.dcoder.springmvc.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private int id;
    private String title;
    private String description;
    private float price;
    private String imageUrl;
    private int categoryId;
}

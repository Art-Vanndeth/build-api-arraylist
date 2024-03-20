package com.dcoder.springmvc.service;

import com.dcoder.springmvc.dto.CategoryRequest;
import com.dcoder.springmvc.dto.CategoryResponse;
import com.dcoder.springmvc.dto.ProductRequest;
import com.dcoder.springmvc.dto.ProductResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAll(String productName);
    CategoryResponse create(CategoryRequest categoryRequest);
    CategoryResponse getById(int id);
    void deleteById(int id);
    CategoryResponse updateById(int id, CategoryRequest categoryRequest);
}

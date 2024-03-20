package com.dcoder.springmvc.service;

import com.dcoder.springmvc.dto.ProductRequest;
import com.dcoder.springmvc.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProduct(String productName);
    ProductResponse createProduct(ProductRequest productRequest);
    ProductResponse findProductByID(int id );
    void deleteProduct(int productId);
    ProductResponse updateProduct(int id ,  ProductRequest productRequest);
}

package com.dcoder.springmvc.repository;



import com.dcoder.springmvc.model.Category;
import com.dcoder.springmvc.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryRepository {
    private final List<Category> categories = new ArrayList<>(){{
        add(Category.builder()
                .id(1)
                .title("Electronic")
                .description("All electronic compartment!")
                .build());
        add(Category.builder()
                .id(2)
                .title("Food")
                .description("Stuff that you can eat!")
                .build());
    }};

    public List<Category> findAll(){
        return categories;
    }

    public Category addCategory(Category category){
        categories.add(category);
        return category;
    }

    public void updateCategory(Category category){
        // need find the index of the product
        int index =   categories.indexOf(
                categories.stream()
                        .filter(pro->pro.getId()==category.getId())
                        .findFirst()
                        .orElse(null)
        ) ;
        categories.set(index,category);
    }
    public void deleteCategory(int id){
        int index =   categories.indexOf(
                categories.stream()
                        .filter(pro->pro.getId()==id)
                        .findFirst()
                        .orElse(null)
        ) ;
        categories.remove(index);
    }
}


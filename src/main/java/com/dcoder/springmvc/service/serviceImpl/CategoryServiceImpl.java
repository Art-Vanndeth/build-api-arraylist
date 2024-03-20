package com.dcoder.springmvc.service.serviceImpl;

import com.dcoder.springmvc.dto.CategoryRequest;
import com.dcoder.springmvc.dto.CategoryResponse;
import com.dcoder.springmvc.dto.ProductRequest;
import com.dcoder.springmvc.model.Category;
import com.dcoder.springmvc.model.Product;
import com.dcoder.springmvc.repository.CategoryRepository;
import com.dcoder.springmvc.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private Category searchCategoryByID(int id){
        return  categoryRepository.findAll()
                .stream().filter(c->c.getId()==id)
                .findFirst()
                .orElseThrow(()->new HttpClientErrorException(HttpStatus.NOT_FOUND,"Category doesn't exist!!"));
    }
    private CategoryResponse mapCategoryToResponse(Category category){
        categoryRepository.findAll();
        var categoryResponse = CategoryResponse.builder()
                .id(category.getId())
                .title(category.getTitle())
                .description(category.getDescription())
                .build();


        return CategoryResponse.builder()
                .id(category.getId())
                .title(category.getTitle())
                .description(category.getDescription())
                .build();
    }
    private Category mapRequestToCategory(CategoryRequest request){
        return Category.builder()
                .title(request.title())
                .description(request.description())
                .build();
    }
    @Override
    public List<CategoryResponse> getAll(String productName) {

        var category = categoryRepository.findAll();
        if (!productName.isEmpty()){
            category = category.stream().filter(
                    pro-> pro.getTitle().toLowerCase().contains(productName.toLowerCase())
            ).toList();
        }
        return  category
                .stream()
                .map(this::mapCategoryToResponse).toList();
    }

    @Override
    public CategoryResponse create(CategoryRequest categoryRequest) {
        Category category = mapRequestToCategory(categoryRequest);
        var maxID = categoryRepository.findAll()
                .stream()
                .max(Comparator.comparingInt(Category::getId))
                .map(Category::getId);
        int newID=1;
        if(maxID.isPresent()) {
            newID = maxID.get() + 1;
        }
        category.setId(newID);
        categoryRepository.addCategory(category);
        return mapCategoryToResponse(category);
    }

    @Override
    public CategoryResponse getById(int id) {
        return mapCategoryToResponse(searchCategoryByID(id));
    }

    @Override
    public void deleteById(int id) {
        categoryRepository.deleteCategory(searchCategoryByID(id).getId());
    }

    @Override
    public CategoryResponse updateById(int id, CategoryRequest categoryRequest) {
        var result = searchCategoryByID(id);
        result= mapRequestToCategory(categoryRequest);
        result.setId(id);
        categoryRepository.updateCategory(result);
        return mapCategoryToResponse(result);
    }
}

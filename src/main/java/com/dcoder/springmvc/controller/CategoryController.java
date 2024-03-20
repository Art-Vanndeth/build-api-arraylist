package com.dcoder.springmvc.controller;

import com.dcoder.springmvc.dto.CategoryRequest;
import com.dcoder.springmvc.dto.ProductRequest;
import com.dcoder.springmvc.service.CategoryService;
import com.dcoder.springmvc.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private Map<String, Object> response(Object object, String message, int status){
        HashMap<String, Object> response = new HashMap<>();
        response.put("payload",object);
        response.put("message", message);
        response.put("status", status);
        return response;
    }
    @GetMapping("/get-all")
    public Map<String, Object> getAllCategory(
            @RequestParam(defaultValue = "" ) String title) {
        return response(
                categoryService.getAll(title),
                "Successfully Retrieved all data!",
                HttpStatus.OK.value()
        );

    }
    @PostMapping("/new-category")
    public Map<String, Object> createNewProduct(@RequestBody CategoryRequest request) {
        return response(
                categoryService.create(request),
                "Created New Category Successfully!",
                HttpStatus.CREATED.value());
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Map<String,Object> getCategoryByID(@PathVariable int id){
        return response(
                categoryService.getById(id),
                "Successfully Retrieved the record!",
                HttpStatus.FOUND.value());
    }

    @PatchMapping("/{id}")
    public Map<String, Object> updateProduct(@PathVariable int id , @RequestBody CategoryRequest request){
        return response(
                categoryService.updateById(id, request),
                "Update Product Successfully",
                HttpStatus.OK.value()
        );
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteProduct(@PathVariable int id){
        categoryService.deleteById(id);
        return response(new ArrayList<>(),"Delete Successfully",HttpStatus.OK.value());
    }
}

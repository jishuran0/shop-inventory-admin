package com.shop.controller;

import com.shop.common.Result;
import com.shop.domain.Category;
import com.shop.service.CategoryService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping("/list")
    public Result<List<Category>> list() {
        return Result.success(categoryService.findAll());
    }

    // 新增分类
    @PostMapping("/add")
    public Result add(@RequestBody Category category){
        categoryService.add(category);
        return Result.success("新增分类成功");
    }

    // 修改分类
    @PutMapping("/update")
    public Result update(@RequestBody Category category){
        categoryService.update(category);
        return Result.success("修改分类成功");
    }

    // 删除分类
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        categoryService.delete(id);
        return Result.success("删除分类成功");
    }
}
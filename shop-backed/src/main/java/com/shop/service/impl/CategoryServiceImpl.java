package com.shop.service.impl;

import com.shop.domain.Category;
import com.shop.mapper.CategoryMapper;
import com.shop.service.CategoryService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findAll() {
        return categoryMapper.selectAll();
    }

    // 实现新增方法
    @Override
    public void add(Category category) {
        categoryMapper.insert(category);
    }

    // 实现修改方法
    @Override
    public void update(Category category) {
        categoryMapper.update(category);
    }

    // 实现删除方法
    @Override
    public void delete(Integer id) {
        categoryMapper.deleteById(id);
    }
}
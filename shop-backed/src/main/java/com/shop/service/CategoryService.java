package com.shop.service;

import com.shop.domain.Category;
import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    // 新增
    void add(Category category);
    // 修改
    void update(Category category);
    // 删除
    void delete(Integer id);
}
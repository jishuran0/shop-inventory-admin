package com.shop.mapper;

import com.shop.domain.Category;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface CategoryMapper {
    List<Category> selectAll();
    // 新增
    void insert(Category category);
    // 修改
    void update(Category category);
    // 删除
    void deleteById(@Param("id") Integer id);
}
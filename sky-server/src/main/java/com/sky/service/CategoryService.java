package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

import java.util.List;

public interface CategoryService {
    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    void openOrClosed(Integer status, Long id);

    void updateCat(CategoryDTO categoryDTO);

    void addCat(CategoryDTO categoryDTO);

    int deleteCat(Long id);

    List<Category> queryByType(Integer type);
}

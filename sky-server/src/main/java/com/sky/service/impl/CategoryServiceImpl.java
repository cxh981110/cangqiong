package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.entity.Employee;
import com.sky.mapper.CategoryMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 分页查询菜品实现方法
     * @param categoryPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        PageHelper.startPage(categoryPageQueryDTO.getPage(),categoryPageQueryDTO.getPageSize());
        Page<Category> page = categoryMapper.pageQuery(categoryPageQueryDTO);
        return new PageResult().setTotal(page.getTotal()).setRecords(page.getResult());
    }

    /**
     * 启用禁用菜品实现方法
     * @param status
     * @param id
     */
    @Override
    public void openOrClosed(Integer status, Long id) {
        Category category = new Category().setStatus(status).setId(id);
        categoryMapper.update(category);
    }

    /**
     * 修改分类实现方法
     * @param categoryDTO
     */
    @Override
    public void updateCat(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO,category);

        categoryMapper.update(category);
    }

    /**
     * 添加分类实现方法
     * @param categoryDTO
     */
    @Override
    public void addCat(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO,category);

        category.setStatus(StatusConstant.ENABLE);
        categoryMapper.add(category);
    }

    /**
     * 删除分类实现方法
     * @param id
     */
    @Override
    public int deleteCat(Long id) {
        if(categoryMapper.delete(id)<1){
            return categoryMapper.delete(id);
        }
        return categoryMapper.delete(id);
    }

    @Override
    public List<Category> queryByType(Integer type) {
        return categoryMapper.queryByType(type);
    }
}

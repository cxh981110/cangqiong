package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 分页查询菜品
     * @param categoryPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    public Result<PageResult> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("分页查询：{}",categoryPageQueryDTO);
        PageResult pageResult = categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 启用禁用菜品
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    public Result openOrClosed(@PathVariable Integer status, Long id){
        log.info("启用禁用菜品：{},{}",status,id);
        categoryService.openOrClosed(status,id);
        return Result.success();
    }

    /**
     * 修改菜品
     * @param categoryDTO
     * @return
     */
    @PutMapping("")
    public Result updateCat(@RequestBody CategoryDTO categoryDTO){
        log.info("修改菜品：{}",categoryDTO);
        categoryService.updateCat(categoryDTO);
        return Result.success();
    }

    /**
     * 新增菜品
     * @param categoryDTO
     * @return
     */
    @PostMapping("")
    public Result addCat(@RequestBody CategoryDTO categoryDTO){
        log.info("新增菜品：{}",categoryDTO);
        categoryService.addCat(categoryDTO);
        return Result.success();
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @DeleteMapping("")
    public Result delete(Long id){
        log.info("删除分类：{}",id);
        int isSuccess = categoryService.deleteCat(id);
        if(isSuccess == 0){
            return Result.error(MessageConstant.Category_Is_Empty);
        }
        return Result.success(isSuccess);
    }
    @GetMapping("/list")
    public Result queryByType(Integer type){
        log.info("按照类型查询菜品：{}",type);
        List<Category> categories = categoryService.queryByType(type);
        if(categories.size()==0) return Result.error(MessageConstant.Type_Is_Empty);
        return Result.success(categories);
    }
}

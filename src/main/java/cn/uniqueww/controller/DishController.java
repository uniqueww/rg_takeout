package cn.uniqueww.controller;


import cn.uniqueww.dto.DishDto;
import cn.uniqueww.entity.Category;
import cn.uniqueww.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;;
import com.baomidou.mybatisplus.extension.api.ApiController;
import cn.uniqueww.common.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.uniqueww.entity.Dish;
import cn.uniqueww.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜品管理(Dish)表控制层
 *
 * @author 罗玉新
 * @since 2022-09-14 12:39:58
 */
@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController extends ApiController {

    @Resource
    private CategoryService categoryService;

    /**
     * 服务对象
     */
    @Resource
    private DishService dishService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param name 查询实体
     * @return 所有数据
     */
    @GetMapping("/page")
    public Result page(int page, int pageSize, String name) {
        //构造分页对象
        Page<Dish> pageInfo = new Page<>(page,pageSize);
        Page<DishDto> dtoPage = new Page<>();

        //构造查询器
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper();
        //查询条件封装
        queryWrapper.like(name != null, Dish::getName, name);

        dishService.page(pageInfo, queryWrapper);

        BeanUtils.copyProperties(pageInfo, dtoPage, "records");
        //数据清洗
        List<Dish> records = pageInfo.getRecords();
        List<DishDto> newRecords = records.stream().map(s -> {
            //创建接收对象
            DishDto dishDto = new DishDto();
            //将数据拷贝过去
            BeanUtils.copyProperties(s, dishDto);
            Long categoryId = s.getCategoryId();
            Category category = categoryService.getById(categoryId);
            //防止空指针
            if (category!=null ){
                dishDto.setCategoryName(category.getName());
            }
            return dishDto;
        }).collect(Collectors.toList());

        dtoPage.setRecords(newRecords);
        return Result.success(dtoPage);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result selectOne(@PathVariable Serializable id) {

        DishDto dishDto = dishService.getWithFlavors(id);

        return Result.success(dishDto);
    }

    /**
     * 新增数据
     *
     * @param dishDto 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result insert(@RequestBody DishDto dishDto) {
        log.info(dishDto.toString());
        dishService.saveWithFlavors(dishDto);
        return Result.success("添加成功");
    }

    /**
     * 修改数据
     *
     * @param dishDto 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result update(@RequestBody DishDto dishDto) {
        dishService.upWithFlavors(dishDto);
        return Result.success("修改成功");
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result delete(@RequestParam("idList") List<Long> idList) {


        return Result.success(this.dishService.removeByIds(idList));
    }
}


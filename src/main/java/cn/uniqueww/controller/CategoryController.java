package cn.uniqueww.controller;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import cn.uniqueww.common.Result;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.uniqueww.entity.Category;
import cn.uniqueww.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 菜品及套餐分类(Category)表控制层
 *
 * @author 罗玉新
 * @since 2022-09-14 11:07:36
 */
@RestController
@RequestMapping("category")
public class CategoryController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private CategoryService categoryService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param category 查询实体
     * @return 所有数据
     */
    @GetMapping("/page")
    public Result selectAll(int pageSize, int page,Category category) {
        Page<Category> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<Category> categoryLambdaQueryWrapper = new LambdaQueryWrapper<>();
        categoryLambdaQueryWrapper.orderByAsc(Category::getSort);
        return Result.success(this.categoryService.page(pageInfo,categoryLambdaQueryWrapper));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result selectOne(@PathVariable Serializable id) {
        return Result.success(this.categoryService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param category 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result insert(@RequestBody Category category) {
        return Result.success(this.categoryService.save(category));
    }

    /**
     * 修改数据
     *
     * @param category 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result update(@RequestBody Category category) {
        return Result.success(this.categoryService.updateById(category));
    }

    /**
     * 删除数据
     *
     * @param ids
     * @return 删除结果
     */
    @DeleteMapping
    public Result delete(@RequestParam("ids") Long ids) {
        categoryService.myRemove(ids);
        return Result.success("删除成功");
    }

    /**
     * 查询不同分类
     * @param category
     * @return
     */
    @GetMapping("/list")
    public Result<List<Category>> list(Category category){
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(category.getType()!=null,Category::getType,category.getType());
        queryWrapper.orderByAsc(Category::getSort);
        List<Category> list = categoryService.list(queryWrapper);
        return Result.success(list);
    }
}


package cn.uniqueww.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import cn.uniqueww.common.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.uniqueww.entity.Dish;
import cn.uniqueww.service.DishService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 菜品管理(Dish)表控制层
 *
 * @author 罗玉新
 * @since 2022-09-14 12:39:58
 */
@RestController
@RequestMapping("dish")
public class DishController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private DishService dishService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param dish 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result selectAll(Page<Dish> page, Dish dish) {
        return Result.success(this.dishService.page(page, new QueryWrapper<>(dish)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result selectOne(@PathVariable Serializable id) {
        return Result.success(this.dishService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param dish 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result insert(@RequestBody Dish dish) {
        return Result.success(this.dishService.save(dish));
    }

    /**
     * 修改数据
     *
     * @param dish 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result update(@RequestBody Dish dish) {
        return Result.success(this.dishService.updateById(dish));
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


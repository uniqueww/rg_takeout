package cn.uniqueww.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import cn.uniqueww.common.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.uniqueww.entity.SetmealDish;
import cn.uniqueww.service.SetmealDishService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 套餐菜品关系(SetmealDish)表控制层
 *
 * @author 罗玉新
 * @since 2022-09-15 10:32:53
 */
@RestController
@RequestMapping("setmealDish")
public class SetmealDishController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SetmealDishService setmealDishService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param setmealDish 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result selectAll(Page<SetmealDish> page, SetmealDish setmealDish) {
        return Result.success(this.setmealDishService.page(page, new QueryWrapper<>(setmealDish)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result selectOne(@PathVariable Serializable id) {
        return Result.success(this.setmealDishService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param setmealDish 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result insert(@RequestBody SetmealDish setmealDish) {
        return Result.success(this.setmealDishService.save(setmealDish));
    }

    /**
     * 修改数据
     *
     * @param setmealDish 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result update(@RequestBody SetmealDish setmealDish) {
        return Result.success(this.setmealDishService.updateById(setmealDish));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result delete(@RequestParam("idList") List<Long> idList) {
        return Result.success(this.setmealDishService.removeByIds(idList));
    }
}


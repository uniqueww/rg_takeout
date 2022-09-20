package cn.uniqueww.controller;



import cn.uniqueww.dto.SetmealDto;
import cn.uniqueww.entity.SetmealDish;
import cn.uniqueww.exception.CustomException;
import cn.uniqueww.service.SetmealDishService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import cn.uniqueww.common.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.uniqueww.entity.Setmeal;
import cn.uniqueww.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.swing.undo.CannotUndoException;
import java.io.Serializable;
import java.util.List;

/**
 * 套餐(Setmeal)表控制层
 *
 * @author 罗玉新
 * @since 2022-09-14 12:41:31
 */
@Slf4j
@RestController
@RequestMapping("setmeal")
public class SetmealController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SetmealService setmealService;

    @Resource
    private SetmealDishService setmealDishService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @return 所有数据
     */
    @GetMapping("/page")
    public Result page(int page, int pageSize, String name) {
        Page<SetmealDto> dtoPage = setmealService.pageList(page, pageSize, name);
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
        SetmealDto setmealDto = setmealService.getWithDishs(id);
        return Result.success(setmealDto);
    }

    /**
     * 新增数据
     *
     * @param setmealDto 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result insert(@RequestBody SetmealDto setmealDto) {
        log.info(setmealDto.toString());
        setmealService.saveWithDishs(setmealDto);
        return Result.success("添加成功");
    }

    /**
     * 修改数据
     *
     * @param setmeal 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result update(@RequestBody Setmeal setmeal) {
        return Result.success(this.setmealService.updateById(setmeal));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result delete(@RequestParam("ids") List<Long> idList) {
        //检验套餐下面是否有菜品
        boolean haveDishs = setmealService.isHaveDishs(idList);
        //有菜品抛出异常信息无法删除，无菜品则删除
        if (haveDishs){
            throw new CustomException("套餐内有关联菜品无法删除");
        }
        LambdaQueryWrapper<SetmealDish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SetmealDish::getSetmealId,idList);
        setmealDishService.remove(queryWrapper);
        return Result.success(this.setmealService.removeByIds(idList));
    }
}


package cn.uniqueww.controller;


import cn.uniqueww.utils.BaseContext;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import cn.uniqueww.common.Result;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.uniqueww.entity.ShoppingCart;
import cn.uniqueww.service.ShoppingCartService;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * 购物车(ShoppingCart)表控制层
 *
 * @author unique
 * @since 2022-11-27 12:30:21
 */
@RestController
@RequestMapping("shoppingCart")
public class ShoppingCartController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private ShoppingCartService shoppingCartService;

    /**
     * 分页查询所有数据
     *
     * @param page         分页对象
     * @param shoppingCart 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result selectAll(Page<ShoppingCart> page, ShoppingCart shoppingCart) {
        return Result.success(this.shoppingCartService.page(page, new QueryWrapper<>(shoppingCart)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result selectOne(@PathVariable Serializable id) {
        return Result.success(this.shoppingCartService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param shoppingCart 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result insert(@RequestBody ShoppingCart shoppingCart) {
        return Result.success(this.shoppingCartService.save(shoppingCart));
    }

    /**
     * 修改数据
     *
     * @param shoppingCart 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result update(@RequestBody ShoppingCart shoppingCart) {
        return Result.success(this.shoppingCartService.updateById(shoppingCart));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result delete(@RequestParam("idList") List<Long> idList) {
        return Result.success(this.shoppingCartService.removeByIds(idList));
    }

    /**
    * 添加至购物车
    * */
    @PostMapping("add")
    public Result add(@RequestBody ShoppingCart shoppingCart){
        //获取当前的用户id
        Long currentId = BaseContext.getCurrentId();
        shoppingCart.setUserId(currentId);
        //检测购物车中是否有该商品
        LambdaQueryWrapper<ShoppingCart> wrapper = new LambdaQueryWrapper<>();
        if (shoppingCart.getDishId()!=null){
            wrapper.eq(ShoppingCart::getUserId,currentId)
                    .eq(ShoppingCart::getDishId,shoppingCart.getDishId());
        } else {
            wrapper.eq(ShoppingCart::getUserId,currentId)
                    .eq(ShoppingCart::getSetmealId,shoppingCart.getSetmealId());
        }
        //已经有该商品 则数量加一
        ShoppingCart one = shoppingCartService.getOne(wrapper);
        if (one!=null){
            Integer number = one.getNumber()+1;
            LambdaUpdateWrapper<ShoppingCart> updateWrapper = new LambdaUpdateWrapper();
            updateWrapper.eq(ShoppingCart::getUserId,currentId)
                    .eq(ShoppingCart::getId,one.getId());
            updateWrapper.set(ShoppingCart::getNumber,number)
                            .set(ShoppingCart::getCreateTime, LocalDateTime.now());
            shoppingCartService.update(updateWrapper);
            return Result.success("添加成功");
        }
        //默认数量为一
        shoppingCart.setNumber(1);
        shoppingCart.setCreateTime(LocalDateTime.now());
        shoppingCartService.save(shoppingCart);
        return Result.success("添加成功");

    }

    @PostMapping("sub")
    public Result sub(@RequestBody ShoppingCart shoppingCart){
        LambdaQueryWrapper<ShoppingCart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ShoppingCart::getUserId,BaseContext.getCurrentId())
                .and(x -> x.eq(ShoppingCart::getSetmealId,shoppingCart.getSetmealId())
                        .or(y->y.eq(ShoppingCart::getDishId,shoppingCart.getDishId())));
        ShoppingCart one = shoppingCartService.getOne(wrapper);
        if (one!=null){
            Integer number = one.getNumber();
            if (number-1==0){
                shoppingCartService.remove(wrapper);
                return Result.success("成功");
            }
            LambdaUpdateWrapper<ShoppingCart> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(ShoppingCart::getUserId,BaseContext.getCurrentId())
                    .and(x -> x.eq(ShoppingCart::getSetmealId,shoppingCart.getSetmealId())
                            .or(y->y.eq(ShoppingCart::getDishId,shoppingCart.getDishId())))
                    .set(ShoppingCart::getNumber,number-1);
            shoppingCartService.update(updateWrapper);
            return Result.success("成功");
        }
        return Result.error("商品减少失败");
    }

    /**
     * 查询购物车
     * */
    @GetMapping("list")
    public Result list(){
        //获取当前用户的id
        Long currentId = BaseContext.getCurrentId();
        LambdaQueryWrapper<ShoppingCart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ShoppingCart::getUserId,currentId)
                .orderByDesc(ShoppingCart::getCreateTime);
        List<ShoppingCart> list = shoppingCartService.list(wrapper);
        return Result.success(list);
    }

    /**
     * 清空购物车
     * */
    @DeleteMapping("clean")
    public Result clear(){
        Long currentId = BaseContext.getCurrentId();
        LambdaQueryWrapper<ShoppingCart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ShoppingCart::getUserId,currentId);
        shoppingCartService.remove(wrapper);
        return Result.success("清空购物车成功");
    }
}

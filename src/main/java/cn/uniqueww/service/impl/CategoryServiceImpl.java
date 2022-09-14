package cn.uniqueww.service.impl;

import cn.uniqueww.entity.Dish;
import cn.uniqueww.entity.Setmeal;
import cn.uniqueww.exception.CustomException;
import cn.uniqueww.service.DishService;
import cn.uniqueww.service.SetmealService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.uniqueww.dao.CategoryDao;
import cn.uniqueww.entity.Category;
import cn.uniqueww.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 菜品及套餐分类(Category)表服务实现类
 *
 * @author 罗玉新
 * @since 2022-09-14 11:07:36
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, Category> implements CategoryService {

    @Resource
    private DishService dishService;

    @Resource
    private SetmealService setmealService;

    @Override
    public void myRemove(Long id) {
        //如果下面关联了菜品则抛出异常
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Dish::getCategoryId,id);
        int count = dishService.count(queryWrapper);
        if (count!=0){
            throw new CustomException("该分类下存在菜品，请删除菜品后尝试");
        }

        //如果关联了套餐则抛出异常
        LambdaQueryWrapper<Setmeal> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(Setmeal::getCategoryId,id);
        int count1 = setmealService.count(queryWrapper1);
        if (count1!=0){
            throw new CustomException("该套餐下存在菜品，请删除菜品后尝试");
        }

        //否则执行删除操作
        super.removeById(id);
    }
}


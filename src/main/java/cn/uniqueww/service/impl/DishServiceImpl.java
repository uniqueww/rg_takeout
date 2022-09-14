package cn.uniqueww.service.impl;

import cn.uniqueww.entity.Setmeal;
import cn.uniqueww.exception.CustomException;
import cn.uniqueww.service.SetmealService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.uniqueww.dao.DishDao;
import cn.uniqueww.entity.Dish;
import cn.uniqueww.service.DishService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 菜品管理(Dish)表服务实现类
 *
 * @author 罗玉新
 * @since 2022-09-14 12:39:58
 */
@Service("dishService")
public class DishServiceImpl extends ServiceImpl<DishDao, Dish> implements DishService {


}


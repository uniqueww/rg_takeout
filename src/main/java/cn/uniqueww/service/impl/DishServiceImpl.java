package cn.uniqueww.service.impl;

import cn.uniqueww.dto.DishDto;
import cn.uniqueww.entity.DishFlavor;
import cn.uniqueww.entity.Setmeal;
import cn.uniqueww.exception.CustomException;
import cn.uniqueww.service.DishFlavorService;
import cn.uniqueww.service.SetmealService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.uniqueww.dao.DishDao;
import cn.uniqueww.entity.Dish;
import cn.uniqueww.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜品管理(Dish)表服务实现类
 *
 * @author 罗玉新
 * @since 2022-09-14 12:39:58
 */
@Service("dishService")
public class DishServiceImpl extends ServiceImpl<DishDao, Dish> implements DishService {

    @Resource
    private DishFlavorService dishFlavorService;

    @Override
    @Transactional
    public void saveWithFlavors(DishDto dishDto) {
        //存储Dish的基本信息
        super.save(dishDto);


        //为Flavor装配dishId
        List<DishFlavor> flavors = dishDto.getFlavors();
        Long id = dishDto.getId();
        flavors = flavors.stream().map(s -> {
            s.setDishId(id);
            return s;
        }).collect(Collectors.toList());

        //存储Flavors
        dishFlavorService.saveBatch(flavors);
    }

    @Override
    public DishDto getWithFlavors(Serializable id) {
        //获取基本的dish信息
        Dish dish = this.getById(id);
        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish,dishDto);
        //组装条件
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(DishFlavor::getDishId,dish.getId());
        //获取Flavors信息
        List<DishFlavor> list = dishFlavorService.list(queryWrapper);

        dishDto.setFlavors(list);
        return dishDto;
    }

    @Override
    @Transactional
    public void upWithFlavors(DishDto dishDto) {
        //删除原来的Flavors
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dishDto.getId());
        dishFlavorService.remove(queryWrapper);

        //更新Dish的基本信息
        super.updateById(dishDto);


        //为Flavor装配dishId
        List<DishFlavor> flavors = dishDto.getFlavors();
        Long id = dishDto.getId();
        flavors = flavors.stream().map(s -> {
            s.setDishId(id);
            return s;
        }).collect(Collectors.toList());

        //存储Flavors
        dishFlavorService.saveBatch(flavors);
    }

    @Override
    public List<Dish> getDishList(Dish dish) {
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Dish::getCategoryId,dish.getCategoryId());
        queryWrapper.eq(Dish::getStatus,1);
        List<Dish> list = this.list(queryWrapper);
        return list;
    }
}


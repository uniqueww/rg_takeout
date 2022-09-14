package cn.uniqueww.service;

import cn.uniqueww.dto.DishDto;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.uniqueww.entity.Dish;

/**
 * 菜品管理(Dish)表服务接口
 *
 * @author 罗玉新
 * @since 2022-09-14 12:39:58
 */
public interface DishService extends IService<Dish> {

    public void saveWithFlavors(DishDto dishDto);

}


package cn.uniqueww.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.uniqueww.dao.SetmealDishDao;
import cn.uniqueww.entity.SetmealDish;
import cn.uniqueww.service.SetmealDishService;
import org.springframework.stereotype.Service;

/**
 * 套餐菜品关系(SetmealDish)表服务实现类
 *
 * @author 罗玉新
 * @since 2022-09-15 10:32:53
 */
@Service("setmealDishService")
public class SetmealDishServiceImpl extends ServiceImpl<SetmealDishDao, SetmealDish> implements SetmealDishService {

}


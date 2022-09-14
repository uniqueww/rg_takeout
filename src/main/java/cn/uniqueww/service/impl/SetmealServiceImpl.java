package cn.uniqueww.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.uniqueww.dao.SetmealDao;
import cn.uniqueww.entity.Setmeal;
import cn.uniqueww.service.SetmealService;
import org.springframework.stereotype.Service;

/**
 * 套餐(Setmeal)表服务实现类
 *
 * @author 罗玉新
 * @since 2022-09-14 12:41:31
 */
@Service("setmealService")
public class SetmealServiceImpl extends ServiceImpl<SetmealDao, Setmeal> implements SetmealService {

}


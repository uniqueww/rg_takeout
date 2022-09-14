package cn.uniqueww.service.impl;

import cn.uniqueww.dto.DishDto;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.uniqueww.dao.DishFlavorDao;
import cn.uniqueww.entity.DishFlavor;
import cn.uniqueww.service.DishFlavorService;
import org.springframework.stereotype.Service;

/**
 * 菜品口味关系表(DishFlavor)表服务实现类
 *
 * @author 罗玉新
 * @since 2022-09-14 14:59:41
 */
@Service("dishFlavorService")
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorDao, DishFlavor> implements DishFlavorService {


}


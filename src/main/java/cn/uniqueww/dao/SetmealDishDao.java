package cn.uniqueww.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.uniqueww.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;
/**
 * 套餐菜品关系(SetmealDish)表数据库访问层
 *
 * @author 罗玉新
 * @since 2022-09-15 10:32:53
 */
@Mapper
public interface SetmealDishDao extends BaseMapper<SetmealDish> {

}


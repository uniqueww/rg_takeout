package cn.uniqueww.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.uniqueww.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

/**
 * 菜品管理(Dish)表数据库访问层
 *
 * @author 罗玉新
 * @since 2022-09-14 12:39:58
 */
@Mapper
public interface DishDao extends BaseMapper<Dish> {

}


package cn.uniqueww.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.uniqueww.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;
/**
 * 菜品口味关系表(DishFlavor)表数据库访问层
 *
 * @author 罗玉新
 * @since 2022-09-14 14:59:41
 */
@Mapper
public interface DishFlavorDao extends BaseMapper<DishFlavor> {

}


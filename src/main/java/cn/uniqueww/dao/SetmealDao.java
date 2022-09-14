package cn.uniqueww.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.uniqueww.entity.Setmeal;
import org.apache.ibatis.annotations.Mapper;
/**
 * 套餐(Setmeal)表数据库访问层
 *
 * @author 罗玉新
 * @since 2022-09-14 12:41:31
 */
@Mapper
public interface SetmealDao extends BaseMapper<Setmeal> {

}


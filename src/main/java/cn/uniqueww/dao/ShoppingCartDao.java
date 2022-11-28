package cn.uniqueww.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.uniqueww.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

/**
 * 购物车(ShoppingCart)表数据库访问层
 *
 * @author unique
 * @since 2022-11-27 12:30:21
 */
@Mapper
public interface ShoppingCartDao extends BaseMapper<ShoppingCart> {

}

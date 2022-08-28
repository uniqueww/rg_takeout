package cn.uniqueww.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.uniqueww.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单表(Orders)表数据库访问层
 *
 * @author lyx
 * @since 2022-08-27 12:58:56
 */
@Mapper
public interface OrdersDao extends BaseMapper<Orders> {

}


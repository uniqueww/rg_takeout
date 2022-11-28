package cn.uniqueww.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.uniqueww.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单明细表(OrderDetail)表数据库访问层
 *
 * @author unique
 * @since 2022-11-27 15:05:54
 */
@Mapper
public interface OrderDetailDao extends BaseMapper<OrderDetail> {

}

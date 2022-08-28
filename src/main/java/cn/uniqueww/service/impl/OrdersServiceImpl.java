package cn.uniqueww.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.uniqueww.dao.OrdersDao;
import cn.uniqueww.entity.Orders;
import cn.uniqueww.service.OrdersService;
import org.springframework.stereotype.Service;

/**
 * 订单表(Orders)表服务实现类
 *
 * @author lyx
 * @since 2022-08-27 12:58:56
 */
@Service("ordersService")
public class OrdersServiceImpl extends ServiceImpl<OrdersDao, Orders> implements OrdersService {

}


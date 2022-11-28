package cn.uniqueww.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.uniqueww.dao.ShoppingCartDao;
import cn.uniqueww.entity.ShoppingCart;
import cn.uniqueww.service.ShoppingCartService;
import org.springframework.stereotype.Service;

/**
 * 购物车(ShoppingCart)表服务实现类
 *
 * @author unique
 * @since 2022-11-27 12:30:21
 */
@Service("shoppingCartService")
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartDao, ShoppingCart> implements ShoppingCartService {

}

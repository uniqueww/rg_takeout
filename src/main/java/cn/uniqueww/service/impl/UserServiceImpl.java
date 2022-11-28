package cn.uniqueww.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.uniqueww.dao.UserDao;
import cn.uniqueww.entity.User;
import cn.uniqueww.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户信息(User)表服务实现类
 *
 * @author unique
 * @since 2022-11-19 13:41:08
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

}

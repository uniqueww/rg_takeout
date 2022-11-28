package cn.uniqueww.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.uniqueww.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息(User)表数据库访问层
 *
 * @author unique
 * @since 2022-11-19 13:41:08
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

}

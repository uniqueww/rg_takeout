package cn.uniqueww.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.uniqueww.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;

/**
 * 地址管理(AddressBook)表数据库访问层
 *
 * @author unique
 * @since 2022-11-26 14:56:58
 */
@Mapper
public interface AddressBookDao extends BaseMapper<AddressBook> {

}

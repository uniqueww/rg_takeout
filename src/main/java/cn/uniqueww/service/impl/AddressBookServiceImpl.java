package cn.uniqueww.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.uniqueww.dao.AddressBookDao;
import cn.uniqueww.entity.AddressBook;
import cn.uniqueww.service.AddressBookService;
import org.springframework.stereotype.Service;

/**
 * 地址管理(AddressBook)表服务实现类
 *
 * @author unique
 * @since 2022-11-26 14:56:58
 */
@Service("addressBookService")
public class AddressBookServiceImpl extends ServiceImpl<AddressBookDao, AddressBook> implements AddressBookService {

}

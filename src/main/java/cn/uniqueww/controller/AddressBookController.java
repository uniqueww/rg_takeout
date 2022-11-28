package cn.uniqueww.controller;


import cn.uniqueww.utils.BaseContext;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import cn.uniqueww.common.Result;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.uniqueww.entity.AddressBook;
import cn.uniqueww.service.AddressBookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * 地址管理(AddressBook)表控制层
 *
 * @author unique
 * @since 2022-11-26 14:56:58
 */
@RestController
@RequestMapping("addressBook")
public class AddressBookController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private AddressBookService addressBookService;

    /**
     * 分页查询所有数据
     *
     * @param page        分页对象
     * @param addressBook 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result selectAll(Page<AddressBook> page, AddressBook addressBook) {
        return Result.success(this.addressBookService.page(page, new QueryWrapper<>(addressBook)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result selectOne(@PathVariable Serializable id) {
        return Result.success(this.addressBookService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param addressBook 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result insert(@RequestBody AddressBook addressBook) {
        Long currentId = BaseContext.getCurrentId();
        addressBook.setUserId(currentId);
        return Result.success(this.addressBookService.save(addressBook));
    }

    /**
     * 修改数据
     *
     * @param addressBook 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result update(@RequestBody AddressBook addressBook) {
        return Result.success(this.addressBookService.updateById(addressBook));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result delete(@RequestParam("idList") List<Long> idList) {
        return Result.success(this.addressBookService.removeByIds(idList));
    }

    /**
     *
     * 默认地址
    * */
    @PutMapping("default")
    public Result defaultLocale(@RequestBody AddressBook addressBook, HttpServletRequest request){
        //设置其他点的默认地址为空
        Long currentId = BaseContext.getCurrentId();
        LambdaUpdateWrapper<AddressBook> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(AddressBook::getUserId,currentId);
        wrapper.set(AddressBook::getIsDefault,0);
        addressBookService.update(wrapper);

        //设置该地址为默认地址
        addressBook.setIsDefault(1);
        addressBookService.updateById(addressBook);
        return Result.success("设置成功");
    }
    /**
     * 查询全部数据
     *
     * */
    @GetMapping("list")
    public Result list(){
        Long currentId = BaseContext.getCurrentId();
        LambdaQueryWrapper<AddressBook> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AddressBook::getUserId,currentId);
        List<AddressBook> list = addressBookService.list(wrapper);
        return Result.success(list);
    }


    /**
     * 查询默认地址
     * */
    @GetMapping("default")
    public Result getDefault(){
        Long currentId = BaseContext.getCurrentId();
        LambdaQueryWrapper<AddressBook> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AddressBook::getUserId,currentId);
        wrapper.eq(AddressBook::getIsDefault,1);
        AddressBook one = addressBookService.getOne(wrapper);

        if (one==null){
            return Result.error("无默认地址");
        }
        return Result.success(one);
    }

}

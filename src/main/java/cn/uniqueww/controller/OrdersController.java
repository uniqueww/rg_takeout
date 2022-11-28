package cn.uniqueww.controller;



import cn.uniqueww.common.Result;
import cn.uniqueww.dto.OrderDto;
import cn.uniqueww.entity.OrderDetail;
import cn.uniqueww.service.OrderDetailService;
import cn.uniqueww.utils.BaseContext;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.uniqueww.entity.Orders;
import cn.uniqueww.service.OrdersService;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单表(Orders)表控制层
 *
 * @author lyx
 * @since 2022-08-27 12:58:56
 */
@RestController
@RequestMapping("order")
public class OrdersController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private OrdersService ordersService;

    @Resource
    private OrderDetailService orderDetailService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param orders 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result selectAll(Page<Orders> page, Orders orders) {
        return Result.success(this.ordersService.page(page, new QueryWrapper<>(orders)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result selectOne(@PathVariable Serializable id) {
        return Result.success(this.ordersService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param orders 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result insert(@RequestBody Orders orders) {
        return Result.success(this.ordersService.save(orders));
    }

    /**
     * 修改数据
     *
     * @param orders 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result update(@RequestBody Orders orders) {
        return Result.success(this.ordersService.updateById(orders));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result delete(@RequestParam("idList") List<Long> idList) {
        return Result.success(this.ordersService.removeByIds(idList));
    }

    /**
     * 提交订单结算
     * */
    @Transactional
    @PostMapping("submit")
    public  Result submit(@RequestBody Orders orders){
        ordersService.submit(orders);

        return Result.success("提交完成");
    }

    /**
     * 分页查询订单
     * */
    @GetMapping("userPage")
    public Result userPage(Integer page,Integer pageSize){
        //当前用户id
        Long currentId = BaseContext.getCurrentId();

        Page<Orders> ordersPage = new Page<>(page,pageSize);
        ordersService.page(ordersPage, new LambdaQueryWrapper<Orders>().eq(Orders::getUserId, currentId));

        Page<OrderDto> orderDtoPage = new Page<>();
        BeanUtils.copyProperties(ordersPage,orderDtoPage,"records");
        //组装数据
        ArrayList<OrderDto> resultRecord = new ArrayList<>();
        List<Orders> records = ordersPage.getRecords();
        records.forEach(x->{
            OrderDto orderDto = new OrderDto();
            BeanUtils.copyProperties(x,orderDto);
            //查询detail数据
            LambdaQueryWrapper<OrderDetail> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(OrderDetail::getOrderId,x.getId());
            List<OrderDetail> list = orderDetailService.list(wrapper);
            orderDto.setOrderDetails(list);
            resultRecord.add(orderDto);
        });
        orderDtoPage.setRecords(resultRecord);
        return Result.success(orderDtoPage);
    }
}


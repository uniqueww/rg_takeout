package cn.uniqueww.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.Data;

/**
 * 订单明细表(OrderDetail)表实体类
 *
 * @author unique
 * @since 2022-11-27 15:05:54
 */
@SuppressWarnings("serial")
@Data
public class OrderDetail extends Model<OrderDetail> {
    //主键
    private Long id;
    //名字
    private String name;
    //图片
    private String image;
    //订单id
    private Long orderId;
    //菜品id
    private Long dishId;
    //套餐id
    private Long setmealId;
    //口味
    private String dishFlavor;
    //数量
    private Integer number;
    //金额
    private Double amount;


    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}

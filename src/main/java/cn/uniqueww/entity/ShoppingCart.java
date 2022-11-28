package cn.uniqueww.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * 购物车(ShoppingCart)表实体类
 *
 * @author unique
 * @since 2022-11-27 12:30:21
 */
@SuppressWarnings("serial")
@Data
public class ShoppingCart extends Model<ShoppingCart> {
    //主键
    private Long id;
    //名称
    private String name;
    //图片
    private String image;
    //主键
    private Long userId;
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
    //创建时间
    private LocalDateTime createTime;


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

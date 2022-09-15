package cn.uniqueww.dto;

import cn.uniqueww.entity.Setmeal;
import cn.uniqueww.entity.SetmealDish;
import lombok.Data;

import java.util.List;


/**
 * @description:
 * @author: 罗玉新
 * @create: 2022-09-15 11:43
 **/
@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    //套餐名称
    private String CategoryName;

}

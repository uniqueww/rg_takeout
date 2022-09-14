package cn.uniqueww.dto;

import cn.uniqueww.entity.Dish;
import cn.uniqueww.entity.DishFlavor;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: 罗玉新
 * @create: 2022-09-14 18:13
 **/
@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors;

    private String categoryName;

}

package cn.uniqueww.dto;

import cn.uniqueww.entity.OrderDetail;
import cn.uniqueww.entity.Orders;
import lombok.Data;

import java.util.List;

/**
 * @author: uniqueww
 * @since: 2022-11-27 16:22
 **/
@Data
public class OrderDto extends Orders {

    private List<OrderDetail> OrderDetails;
}

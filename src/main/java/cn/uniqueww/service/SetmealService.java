package cn.uniqueww.service;

import cn.uniqueww.dto.SetmealDto;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.uniqueww.entity.Setmeal;

/**
 * 套餐(Setmeal)表服务接口
 *
 * @author 罗玉新
 * @since 2022-09-14 12:41:31
 */
public interface SetmealService extends IService<Setmeal> {

    public void saveWithDishs(SetmealDto setmealDto);

    public Page<SetmealDto> pageList(int page, int pageSize, String name);

}


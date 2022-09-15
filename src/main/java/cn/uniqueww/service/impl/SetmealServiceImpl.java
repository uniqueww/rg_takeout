package cn.uniqueww.service.impl;

import cn.uniqueww.dto.SetmealDto;
import cn.uniqueww.entity.Category;
import cn.uniqueww.entity.SetmealDish;
import cn.uniqueww.service.CategoryService;
import cn.uniqueww.service.SetmealDishService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.uniqueww.dao.SetmealDao;
import cn.uniqueww.entity.Setmeal;
import cn.uniqueww.service.SetmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 套餐(Setmeal)表服务实现类
 *
 * @author 罗玉新
 * @since 2022-09-14 12:41:31
 */
@Service("setmealService")
public class SetmealServiceImpl extends ServiceImpl<SetmealDao, Setmeal> implements SetmealService {

    @Resource
    private CategoryService categoryService;

    @Resource
    private SetmealDishService setmealDishService;


    @Override
    @Transactional
    public void saveWithDishs(SetmealDto setmealDto) {
        //存储基本数据
        this.save(setmealDto);

        //获取提交的菜品
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        //对菜品数据进行处理储存
        List<SetmealDish> list = setmealDishes.stream().map(item -> {
            item.setSetmealId(setmealDto.getId().toString());
            return item;
        }).collect(Collectors.toList());

        setmealDishService.saveBatch(list);

    }

    @Override
    public Page<SetmealDto> pageList(int page, int pageSize, String name) {
        //查出基本数据
        Page<Setmeal> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name!=null,Setmeal::getName,name);
        queryWrapper.orderByAsc(Setmeal::getUpdateTime);
        this.page(pageInfo,queryWrapper);

        //数据拷贝
        Page<SetmealDto> dtoPage = new Page<>();
        BeanUtils.copyProperties(pageInfo,dtoPage,"records");
        //records数据处理
        List<Setmeal> list = pageInfo.getRecords();
        List<SetmealDto> setmealDtos = list.stream().map(item -> {
            //通过关联的id来查询名称
            Long id = item.getCategoryId();
            Category category = categoryService.getById(id);
            SetmealDto setmealDto = new SetmealDto();
            //拷贝基础数据
            BeanUtils.copyProperties(item, setmealDto);
            //组装名称
            setmealDto.setCategoryName(category.getName());
            return setmealDto;
        }).collect(Collectors.toList());

        dtoPage.setRecords(setmealDtos);

        return dtoPage;
    }
}


package cn.uniqueww.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.uniqueww.entity.Category;

/**
 * 菜品及套餐分类(Category)表服务接口
 *
 * @author 罗玉新
 * @since 2022-09-14 11:07:36
 */
public interface CategoryService extends IService<Category> {
    public void myRemove(Long id);
}


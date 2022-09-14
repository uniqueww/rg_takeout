package cn.uniqueww.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.uniqueww.entity.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * 菜品及套餐分类(Category)表数据库访问层
 *
 * @author 罗玉新
 * @since 2022-09-14 11:07:36
 */
@Mapper
public interface CategoryDao extends BaseMapper<Category> {

}


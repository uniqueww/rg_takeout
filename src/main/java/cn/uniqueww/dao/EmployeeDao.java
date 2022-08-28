package cn.uniqueww.dao;

import cn.uniqueww.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * 员工信息(Employee)表数据库访问层
 *
 * @author lyx
 * @since 2022-08-20 12:43:07
 */
@Mapper
public interface EmployeeDao extends BaseMapper<Employee> {



}


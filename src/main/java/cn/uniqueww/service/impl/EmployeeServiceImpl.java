package cn.uniqueww.service.impl;

import cn.uniqueww.entity.Employee;
import cn.uniqueww.dao.EmployeeDao;
import cn.uniqueww.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 员工信息(Employee)表服务实现类
 *
 * @author lyx
 * @since 2022-08-20 12:43:08
 */
@Service("employeeService")
public class EmployeeServiceImpl extends ServiceImpl<EmployeeDao,Employee> implements EmployeeService{
    @Resource
    private EmployeeDao employeeDao;

}

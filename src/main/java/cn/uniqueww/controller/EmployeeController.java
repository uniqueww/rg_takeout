package cn.uniqueww.controller;

import cn.uniqueww.common.Result;
import cn.uniqueww.entity.Employee;
import cn.uniqueww.service.EmployeeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * 员工信息(Employee)表控制层
 *
 * @author lyx
 * @since 2022-08-20 12:43:07
 */
@RestController
@RequestMapping("employee")
@Slf4j
public class EmployeeController {
    /**
     * 服务对象
     */
    @Resource
    private EmployeeService employeeService;


    /**
     * 员工登录
     *
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public Result<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {
        //登陆密码md5加密
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));

        //查询对比
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee one = employeeService.getOne(queryWrapper);

        if (one == null) {
            return Result.error("用户不存在");
        }
        if (!password.equals(one.getPassword())) {
            return Result.error("密码错误");
        }
        if (one.getStatus() == 0) {
            return Result.error("账户已被禁用");
        }
        //登录成功
        request.getSession().setAttribute("employee", one.getId());
        return Result.success(one);
    }

    /**
     * 员工登出
     *
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("employee");
        log.info("登出成功");
        return Result.success("登出成功");
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Employee> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.employeeService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param employee 实体
     * @return 新增结果
     */
    @PostMapping
    public Result<String> add(HttpServletRequest request, @RequestBody Employee employee) {
        log.info("新增的员工信息：{}", employee.toString());
        //设置初始密码12345，进行md5加密
        employee.setPassword(DigestUtils.md5DigestAsHex("12345".getBytes(StandardCharsets.UTF_8)));
        //设置更新信息
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        //设置创建用户的Id
        Long id = (Long) request.getSession().getAttribute("employee");
        employee.setCreateUser(id);
        employee.setUpdateUser(id);
        employeeService.save(employee);
        return Result.success("新增成功！");
    }

    /**
     * 分页查询
     *
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public Result<Page> list(int page, int pageSize, String name) {
        //创建查询类
        Page pageInfo = new Page(page,pageSize);
        //创建查询条件
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(name),Employee::getName,name);
        queryWrapper.orderByDesc(Employee::getUpdateTime);

        //查询
        employeeService.page(pageInfo,queryWrapper);
        return Result.success(pageInfo);
    }

    /**
     * 更新数据
     *
     * @param employee
     * @return
     */
    @PutMapping
    public Result<String> update(HttpServletRequest request,@RequestBody Employee employee){
        employee.setUpdateUser((Long) request.getSession().getAttribute("employee"));
        employee.setUpdateTime(LocalDateTime.now());
        employeeService.updateById(employee);
        return Result.success("更新成功");
    }


}


package cn.uniqueww.controller;


import cn.uniqueww.common.Result;
import cn.uniqueww.utils.ValidateCodeUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.uniqueww.entity.User;
import cn.uniqueww.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.management.ObjectName;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 用户信息(User)表控制层
 *
 * @author unique
 * @since 2022-11-19 13:41:09
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param user 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result selectAll(Page<User> page, User user) {
        return Result.success(this.userService.page(page, new QueryWrapper<>(user)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result selectOne(@PathVariable Serializable id) {
        return Result.success(this.userService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param user 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result insert(@RequestBody User user) {
        return Result.success(this.userService.save(user));
    }

    /**
     * 修改数据
     *
     * @param user 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result update(@RequestBody User user) {
        return Result.success(this.userService.updateById(user));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result delete(@RequestParam("idList") List<Long> idList) {
        return Result.success(this.userService.removeByIds(idList));
    }

    /**
    * 短信
    * */
    @RequestMapping("sendMsg")
    public Result sendMsg(@RequestBody User user, HttpServletRequest request){
        //获取手机号码
        Assert.notNull(user.getPhone(),"手机号码不能为控");
        String phone = user.getPhone();
        //生成验证码
        String code = ValidateCodeUtils.generateValidateCode(4).toString();
        log.info("验证码为：{}",code);

        //存入session中
        request.getSession().setAttribute("code",code);


        return Result.success("发送成功");
    }


    /**
     * 登录
     */
    @PostMapping("login")
    public Result login(@RequestBody Map map, HttpServletRequest request,HttpServletResponse response){
        String code = map.get("code").toString();
        String phone = map.get("phone").toString();
        if (StringUtils.hasLength(code)){
            String codeInSession = request.getSession().getAttribute("code").toString();
            if (codeInSession.equals(code)){
                //是否是新用户
                LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(User::getPhone,phone);

                User user = userService.getOne(wrapper);
                if (user== null){
                    user = new User();
                    user.setPhone(phone);
                    //存入新对象
                    userService.save(user);
                }
                request.getSession().setAttribute("user",user.getId());
                return Result.success("登录成功");
            }
        }
        return Result.error("验证码错误");
    }
}

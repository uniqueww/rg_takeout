package cn.uniqueww.filter;


import cn.uniqueww.common.Result;
import cn.uniqueww.utils.BaseContext;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @description:
 * @author: 罗玉新
 * @create: 2022-08-21 20:06
 **/
@Slf4j
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    public static final AntPathMatcher ANT_PATH_MATCHER =  new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //获取URL
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();

        //不需要拦截的资源
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**"
        };
        //请求链接不在不需要拦截的资源里
        if (!check(url,urls)){
            //未登录
            if (request.getSession().getAttribute("employee")==null){
                response.getWriter().write(JSON.toJSONString(Result.error("NOTLOGIN")));
                log.info("拦截到的URL为{}",url);
                return;
            }
        }

        //必须放在doFilter之前否则不生效
        BaseContext.setCurrentId((Long) request.getSession().getAttribute("employee"));
        log.info("目前的用户Id是:{}",BaseContext.getCurrentId());
        //已经登录不做处理
        filterChain.doFilter(request,response);
        return;
    }

    /**
     * 检测本次请求是否需要放行
     *
     * @param urls
     * @return
     */
    public boolean check(String url,String[] urls){
        for (String s : urls) {
            boolean match = ANT_PATH_MATCHER.match(s, url);
            if (match){
                return match;
            }
        }
        return false;
    }
}

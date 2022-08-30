package cn.uniqueww.utils;

/**
 * @description:从线程中获取http中数据用于数据自动填充
 * @author: 罗玉新
 * @create: 2022-08-30 20:18
 **/
public class BaseContext {


    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    public static Long getCurrentId(){
        return threadLocal.get();
    }

}

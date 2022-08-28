package cn.uniqueww.common;

import lombok.Data;
import java.util.HashMap;
import java.util.Map;


/**
 * 返回结果类的封装
 * @param <T>
 */
@Data
public class Result<T> {
    //编码：1成功，0和其它数字为失败
    private Integer code;
    //错误信息
    private String msg;
    //数据
    private T data;
    //动态数据
    private Map map = new HashMap();

    public static <T> Result<T> success(T object) {
        Result<T> res = new Result<T>();
        res.data = object;
        res.code = 1;
        return res;
    }


    public static <T> Result<T> error(String msg) {
        Result res = new Result();
        res.msg = msg;
        res.code = 0;
        return res;
    }

    public Result<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }



}


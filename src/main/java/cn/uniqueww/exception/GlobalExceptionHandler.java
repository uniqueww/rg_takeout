package cn.uniqueww.exception;

import cn.uniqueww.common.Result;
import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author: 罗玉新
 * @create: 2022-08-27 12:18
 **/
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<String> ExHandler(Exception ex){
        log.info(ex.getMessage());
        if (ex.getMessage().contains("Duplicate entry")){
            String message = ex.getMessage().split(" ")[9]+"已存在";
            return Result.error(message);

        }

        return Result.error("未知异常");
    }

    /**
     * 重载
     * @param ex
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public Result<String> ExHandler(CustomException ex){
        log.info(ex.getMessage());
        return Result.error(ex.getMessage());
    }


}

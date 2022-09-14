package cn.uniqueww.exception;

/**
 * @description:
 * @author: 罗玉新
 * @create: 2022-09-14 12:56
 **/
public class CustomException extends RuntimeException {
    public CustomException (String message){
        super(message);
    }
}

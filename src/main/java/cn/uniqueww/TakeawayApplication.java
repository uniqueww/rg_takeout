package cn.uniqueww;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @description:
 * @author: 罗玉新
 * @create: 2022-08-20 11:16
 **/
@Slf4j
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
public class TakeawayApplication {
    public static void main(String[] args) {
        SpringApplication.run(TakeawayApplication.class,args);
        log.info("项目启动成功。。。。");
    }
}

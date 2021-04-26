package cbuc.life.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: cbuc
 * @date: 2021-04-19 21:32
 * @description:
 */
@SpringBootApplication
public class BaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
        System.out.println("------------------ base module start ------------------");
    }

}

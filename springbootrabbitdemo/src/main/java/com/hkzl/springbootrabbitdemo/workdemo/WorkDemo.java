package com.hkzl.springbootrabbitdemo.workdemo;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author hkzl
 * @version 1.0
 * @date 2020/10/21 16:05
 */
@Component
public class WorkDemo {

    /**
     * 监听到后执行
     * @param message
     */
    @RabbitListener(queuesToDeclare = @Queue(value = "testwork"))
    public void myexit(String message){
        System.out.println("a " + message);
    }


    @RabbitListener(queuesToDeclare = @Queue(value = "testwork"))
    public void myexit2(String message){
        System.out.println("b " + message);
    }

    @RabbitListener(queuesToDeclare = @Queue(value = "testwork"))
    public void myexit3(String message){
        System.out.println("c " + message);
    }
}

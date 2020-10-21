package com.hkzl.springbootrabbitdemo.helloworddemo;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author hkzl
 * @version 1.0
 * @date 2020/10/21 16:05
 */
@Component // 持久化 非独占 不是自动删除队列
@RabbitListener(queuesToDeclare = @Queue(value = "bootDemo"))
public class HelloCustomer {

    /**
     * 监听到后执行
     * @param message
     */
    @RabbitHandler
    public void myexit(String message){
        System.out.println(message);
    }

}

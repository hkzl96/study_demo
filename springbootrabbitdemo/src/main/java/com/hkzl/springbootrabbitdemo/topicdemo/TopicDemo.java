package com.hkzl.springbootrabbitdemo.topicdemo;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author hkzl
 * @version 1.0
 * @date 2020/10/21 17:09
 */
@Component
public class TopicDemo {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, // 临时队列
                    exchange = @Exchange(value = "topicdemo",type = "topic"), // value交换机的名字
                    key = {"user.info","user.*"}
            )
    })
    public void receivel(String msg){
        System.out.println("A "+msg);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "topicdemo",type = "topic"),
                    key = {"user.info"}
            )
    })
    public void receive2(String msg){
        System.out.println("B "+msg);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "topicdemo",type = "topic"),
                    key = {"#"}
            )
    })
    public void receive3(String msg){
        System.out.println("C "+msg);
    }
}

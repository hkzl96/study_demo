package com.hkzl.springbootrabbitdemo.directdemo;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author hkzl
 * @version 1.0
 * @date 2020/10/21 16:49
 */
@Component
public class DirectDemo {


    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, // 临时队列
                    exchange = @Exchange(value = "directdemo",type = "direct"), // value交换机的名字 , type 默认为direct
                    key = {"info","error","warn"}
            )
    })
    public void receivel(String msg){
        System.out.println("A "+msg);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "directdemo",type = "direct"),
                    key = {"info"}
            )
    })
    public void receive2(String msg){
        System.out.println("B "+msg);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "directdemo",type = "direct"),
                    key = {"#"}
            )
    })
    public void receive3(String msg){
        System.out.println("C "+msg);
    }
}

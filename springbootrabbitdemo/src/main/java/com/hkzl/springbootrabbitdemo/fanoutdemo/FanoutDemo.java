package com.hkzl.springbootrabbitdemo.fanoutdemo;

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
public class FanoutDemo {


    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, // 临时队列
                    exchange = @Exchange(value = "fanoutdemo",type = "fanout") // value交换机的名字
            )
    })
    public void receivel(String msg){
        System.out.println("A "+msg);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "fanoutdemo",type = "fanout")
            )
    })
    public void receive2(String msg){
        System.out.println("B "+msg);
    }
}

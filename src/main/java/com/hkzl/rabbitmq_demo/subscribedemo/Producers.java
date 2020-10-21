package com.hkzl.rabbitmq_demo.subscribedemo;

import com.hkzl.rabbitmq_demo.util.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * 广播模式生产者
 * @author hkzl
 * @version 1.0
 * @date 2020/10/19 17:09
 */
public class Producers {
    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        // 将通道声明指定交换机
        // 参数1：交换机名称
        // 参数2：交换机类型fanout广播类型
        channel.exchangeDeclare("logs", "fanout");

        // 发送消息
        channel.basicPublish("logs", "", null, "fanout type message".getBytes());
        // 释放资源1
        RabbitMQUtils.closeConnectionAndChanel(channel, connection);
    }

}

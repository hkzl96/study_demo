package com.hkzl.rabbitmq_demo.topic_demo;

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
        channel.exchangeDeclare("logs_topic", "topic");

        // 发送消息
        // 参数1 交换机名称,
        // 参数二 路由控制
        //
        // 参数四 信息
//        channel.basicPublish("logs", "info", null, "info fanout type message".getBytes());
        channel.basicPublish("logs_topic", "user.info.admin", null, " Direct type message".getBytes());
        // 释放资源1
        RabbitMQUtils.closeConnectionAndChanel(channel, connection);
    }

}

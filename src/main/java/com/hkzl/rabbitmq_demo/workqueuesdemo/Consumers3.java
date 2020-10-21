package com.hkzl.rabbitmq_demo.workqueuesdemo;

import com.hkzl.rabbitmq_demo.util.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 权重
 * @author hkzl
 * @version 1.0
 * @date 2020/10/19 16:29
 */
public class Consumers3 {
    public static void main(String[] args) {
        try {
            //通过工具类获取连接对象
            Connection connection= RabbitMQUtils.getConnection();
            //获取连接中通道 可以通过通道去创建队列和发送信息
            Channel channel = connection.createChannel();
            // 每次只消费一条信息
            channel.basicQos(1);
            // 消费信息
            // 参数2 为自动确认消费,改为false
            channel.basicConsume("workQueue",false,new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println("C3 message = " + new String(body));
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

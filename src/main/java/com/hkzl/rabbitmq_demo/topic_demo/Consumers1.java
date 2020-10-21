package com.hkzl.rabbitmq_demo.topic_demo;

import com.hkzl.rabbitmq_demo.util.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 权重
 * @author hkzl
 * @version 1.0
 * @date 2020/10/19 16:29
 */
public class Consumers1 {
    public static void main(String[] args) {
        try {
            Connection connection= RabbitMQUtils.getConnection();
            Channel channel = connection.createChannel();
            // 创建临时队列
            String queue = channel.queueDeclare().getQueue();
            // 将临时队列和交换机绑定
            // 参数1 队列名
            // 参数2 交换机名
            // 参数3 路由名
            channel.queueBind(queue,"logs_topic","*.info.*");
            // 从队列中消费信息
            channel.basicConsume(queue,false,new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println("C1 message = " + new String(body));
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

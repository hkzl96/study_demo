package com.hkzl.rabbitmq_demo.workqueuesdemo;

import com.hkzl.rabbitmq_demo.util.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 轮询
 * @author hkzl
 * @version 1.0
 * @date 2020/10/19 16:29
 */
public class Consumers2 {
    public static void main(String[] args) {
        try {
            //通过工具类获取连接对象
            Connection connection= RabbitMQUtils.getConnection();
            //获取连接中通道 可以通过通道去创建队列和发送信息
            Channel channel = connection.createChannel();
            //通道绑定对应消息队列
            //参数1：队列名称如果队列不存在自动创建
            //参数2：用来定义队列特性是否要持久化true持久化队列false不持久化
            //参数3：exclusive是否独占队列true独占队列false不独占
            //参数4：autopelete：是否在消费完成后自动删除队列 true自动删除false不自动删除
            //参数5：额外附加参数

//            channel.queueDeclare("helloworddemo",false,false,false,null);

            // 消费信息
            channel.basicConsume("workQueue",true,new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println("C2 message = " + new String(body));
                }
            });
            //调用工具类
//            RabbitMQUtils.closeConnectionAndChanel(channel,connection);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

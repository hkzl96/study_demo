package com.hkzl.rabbitmq_demo.workqueuesdemo;

import com.hkzl.rabbitmq_demo.util.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @author hkzl
 * @version 1.0
 * @date 2020/10/19 15:39
 */
public class Producers {

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
            channel.queueDeclare("workQueue",false,false,false,null);
            //发布消息
            //参数1：交换机名称参数2：队列名称参数3：传递消息额外设置参数4：消息的具体内容

            for (int i = 0; i < 10 ; i ++) {
                channel.basicPublish("","workQueue",null,(i+"hello rabbitmq").getBytes());
            }
            /*channel.close();connection.close();*/
            //调用工具类
            RabbitMQUtils.closeConnectionAndChanel(channel,connection);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

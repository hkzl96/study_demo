package com.hkzl.rabbitmq_demo.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author hkzl
 * @version 1.0
 * @date 2020/10/19 15:26
 */
public class RabbitMQUtils {

    private static ConnectionFactory connectionFactory;
//    private static Properties properties;

    static {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("120.25.249.140");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/helloworddemo");
        connectionFactory.setUsername("hkzl");
        connectionFactory.setPassword("hkzl");
    }

    public static Connection getConnection() {
        try {
            return connectionFactory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭通道和关闭连接工具方法
     */
    public static void closeConnectionAndChanel(Channel channel, Connection conn) {
        try {
            if (channel != null) {
                channel.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

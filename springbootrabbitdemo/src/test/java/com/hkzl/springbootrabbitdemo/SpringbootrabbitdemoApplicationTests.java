package com.hkzl.springbootrabbitdemo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = SpringbootrabbitdemoApplication.class)
@RunWith(SpringRunner.class)
class SpringbootrabbitdemoApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;



    @Test
    void testTopic(){
        rabbitTemplate.convertAndSend("topicdemo","user.info","user.info send");
        rabbitTemplate.convertAndSend("topicdemo","user.error","user.error send");
        rabbitTemplate.convertAndSend("topicdemo","admin","admin send");
    }

    @Test
    void testRoute(){
        rabbitTemplate.convertAndSend("directdemo","info","route info send");
        rabbitTemplate.convertAndSend("directdemo","error","route info send");
    }

    /**
     * 广播测试
     */
    @Test
    void testFanout(){
        for (int i = 0 ; i<4 ; i++) {
            rabbitTemplate.convertAndSend("fanoutdemo","","fanout send"+i);
        }
    }

    /**
     * 轮询测试
     */
    @Test
    void testWork(){
        for(int i = 0; i<12 ; i++){
            System.out.println(i);
            rabbitTemplate.convertAndSend("testwork",("work send"+i));
        }
    }

    /**
     * 点对点测试
     */
    @Test
    void testHello() {
        rabbitTemplate.convertAndSend("bootDemo","hello rabbitmq");
    }

}

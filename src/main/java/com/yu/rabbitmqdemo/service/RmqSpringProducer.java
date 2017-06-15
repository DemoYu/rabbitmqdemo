package com.yu.rabbitmqdemo.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/6/12 0012.
 */
@Service
public class RmqSpringProducer {
    @Resource
    private AmqpTemplate rabbitTemplate;

    public boolean sendMessage(String message){
        try {
            rabbitTemplate.convertAndSend("directExchangeTest",message);
            return  true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}

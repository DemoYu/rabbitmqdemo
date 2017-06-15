package com.yu.rabbitmqdemo.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/6/12 0012.
 */
public class RmqSpringConsumer implements MessageListener{

    public void onMessage(Message message) {
        try {
            System.out.println(new String(message.getBody(),"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}

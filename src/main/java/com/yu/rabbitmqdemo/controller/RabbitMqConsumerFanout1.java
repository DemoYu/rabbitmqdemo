package com.yu.rabbitmqdemo.controller;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2017/6/8 0008.
 */
public class RabbitMqConsumerFanout1 {
    public static void  main(String args[]) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("root");
        Connection connection =  connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("fanoutExchange","fanout");
        channel.queueDeclare("fanoutQueue1",false,false,false,null);
        channel.queueBind("fanoutQueue1","fanoutExchange","");
        channel.queueBind("fanoutQueue1","fanoutExchange1","");
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws UnsupportedEncodingException {
                System.out.println(new String(body,"utf-8"));
            }
        };
        channel.basicConsume("fanoutQueue1", true, consumer);

    }
}

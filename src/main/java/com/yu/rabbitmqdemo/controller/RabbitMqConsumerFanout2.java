package com.yu.rabbitmqdemo.controller;

import com.rabbitmq.client.*;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/6/8 0008.
 */
public class RabbitMqConsumerFanout2 {
    public static void main(String args[])throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("root");
        Connection connection =  connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("fanoutExchange","fanout");
        channel.queueDeclare("fanoutQueue2",false,false,false,null);
        channel.queueBind("fanoutQueue2","fanoutExchange","");
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws UnsupportedEncodingException {
                System.out.println(new String(body,"utf-8"));
            }
        };
        channel.basicConsume("fanoutQueue2", true, consumer);
    }
}

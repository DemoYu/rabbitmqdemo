package com.yu.rabbitmqdemo.controller;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2017/6/8 0008.
 */
public class RabbitMqProducerFanout1 {
    public static  void  main(String args[]) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("root");
        Connection connection =  connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("fanoutExchange","fanout");
      //  channel.queueDeclare("fanoutQueue",false,false,false,null);
        String message = "The message of RabbitMq Fanout Exchange1";
        channel.basicPublish("fanoutExchange","",null, message.getBytes());

    }
}

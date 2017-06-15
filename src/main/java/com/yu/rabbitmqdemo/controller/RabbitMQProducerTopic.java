package com.yu.rabbitmqdemo.controller;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2017/6/12 0012.
 */
public class RabbitMQProducerTopic {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("root");
        Connection connection =  connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("RabbitMQTopic","topic",false);
        String message = "The message of RabbitMq Topic Exchange1";
        channel.basicPublish("RabbitMQTopic","topic.key.*",null,message.getBytes());

    }
}

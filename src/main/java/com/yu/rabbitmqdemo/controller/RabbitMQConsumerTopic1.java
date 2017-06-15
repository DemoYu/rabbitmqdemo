package com.yu.rabbitmqdemo.controller;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2017/6/12 0012.
 */
public class RabbitMQConsumerTopic1 {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("root");
        Connection connection =  connectionFactory.newConnection();
        Channel channel =connection.createChannel();
        channel.queueDeclare("topicQueue1",false,false,false,null);
        channel.queueBind("topicQueue1","RabbitMQTopic","topic.key.*");
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws UnsupportedEncodingException {
                System.out.println(new String(body,"utf-8"));
            }
        };
        channel.basicConsume("topicQueue1",consumer);

    }
}

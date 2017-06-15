package com.yu.rabbitmqdemo.controller;

import com.rabbitmq.client.*;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/6/12 0012.
 */
public class RabbitMQConsumerTopic2 {
    public static void main(String[] args) throws  Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("root");
        Connection connection =  connectionFactory.newConnection();
        Channel channel =connection.createChannel();
        channel.queueDeclare("topicQueue2",false,false,false,null);
        channel.queueBind("topicQueue2","RabbitMQTopic","topic.#");
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws UnsupportedEncodingException {
                System.out.println(new String(body,"utf-8"));
            }
        };
        channel.basicConsume("topicQueue2",consumer);
    }
}

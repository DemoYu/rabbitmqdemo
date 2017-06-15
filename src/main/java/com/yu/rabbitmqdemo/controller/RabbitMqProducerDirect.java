package com.yu.rabbitmqdemo.controller;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2017/6/5 0005.
 */
public class RabbitMqProducerDirect {
   public static void main(String args[]) throws IOException, TimeoutException {

       ConnectionFactory connectionFactory = new ConnectionFactory();
       connectionFactory.setHost("localhost");
       connectionFactory.setPort(5672);
       connectionFactory.setUsername("root");
       connectionFactory.setPassword("root");
       Connection connection = connectionFactory.newConnection();
       Channel channel = connection.createChannel();
       channel.exchangeDeclare("directExchange","direct");
       channel.queueDeclare("directQueue",false,false,false,null);
       channel.queueBind("directQueue","directExchange","directExchangeTest");
       String message = "The message of RabbitMQ test";
       channel.basicPublish("directExchange","directExchangeTest",null,message.getBytes());

   }
}

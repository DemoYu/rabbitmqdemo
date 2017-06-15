package com.yu.rabbitmqdemo.controller;


import com.yu.rabbitmqdemo.service.RmqSpringProducer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.TimeoutException;


/**
 * Created by Administrator on 2017/6/6 0006.
 */
@Controller
public class Demo {
    @Resource
    private RmqSpringProducer rmqSpringProducer;
    @RequestMapping("/demo")
    public String demo() throws IOException, TimeoutException {
        String message = "The message of RabbitMQ test";
        String test = "test";
        boolean b = rmqSpringProducer.sendMessage(message);
       if(b){
           return "success";
       }
       return  "fail";
    }
}

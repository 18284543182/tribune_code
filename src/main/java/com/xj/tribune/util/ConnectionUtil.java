package com.xj.tribune.util;


import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.xj.tribune.rabbit.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author xj
 * @date 2022-04-10
 */
@Component
@Slf4j
public class ConnectionUtil {

    @Autowired
    private RabbitConfig config;

    /**
     * 建立与RabbitMQ的连接
     *
     * @return
     * @throws Exception
     */
    public Connection getConnection() {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost(config.getHostName());
        //端口
        factory.setPort(factory.getPort());
        //设置账号信息，用户名、密码、vhost
        //设置虚拟机，一个mq服务可以设置多个虚拟机，每个虚拟机就相当于一个独立的mq
        factory.setVirtualHost(config.getVirtualHost());
        factory.setUsername(config.getUsername());
        factory.setPassword(config.getPassword());
        log.info("^^^^^^^^^^^^^^^^rabbitmq connection param Msg is {}" + config.toString());
        // 通过工厂获取连接
        Connection connection = null;
        try {
            connection = factory.newConnection();
        } catch (Exception e) {
            log.error("^^^^^^^^^^^^^^^rabbitMq connection error msg is {}", e);
        }
        return connection;
    }
}

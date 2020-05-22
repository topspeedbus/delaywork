package com.chan.delaywork.config;

import jodd.util.StringUtil;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @date 2020/4/7
 */
@Configuration
public class RedissonConfig {
    /**
     * redis集群节点
     */
    @Value("${spring.redis.cluster.nodes}")
    private List<String> nodes;

    /**
     * redis密码
     */
    @Value("${spring.redis.password}")
    private String password;

    /**
     * redis 单节点ip
     */
    @Value("${spring.redis.host}")
    private String host;

    /**
     * redis单节点端口
     */
    @Value("${spring.redis.port}")
    private String port;

    /**
     * 最大连接数
     */
//    @Value("spring.redis.cluster.nodes")
//    private int maxPoolSize;

    @Bean
    public RedissonClient getRedissonClient() {
        RedissonClient client = null;
        Config config = new Config();
        //单节点
        if (!StringUtil.isEmpty(host)) {
            config.useSingleServer().
                    setAddress("redis://" + host + ":" + port)
//                    .setPassword(password)
//                    .setConnectionPoolSize(maxPoolSize)
                    //最小空闲连接
                    .setConnectionMinimumIdleSize(0);
            //如果密码不为空，设置密码
            if (!StringUtil.isEmpty(password)) {
                config.useSingleServer().setPassword(password);
            }
            client = Redisson.create(config);
        } else {
            //集群节点
            String[] nodeAddress = nodes.stream().toArray(String[]::new);
            //这是用的集群server
            config.useClusterServers()
                    //设置集群状态扫描时间2000
                    .setScanInterval(2000)
                    .addNodeAddress(nodeAddress)
//                    .setPassword(password)
//                    .setMasterConnectionPoolSize(maxPoolSize)
                    //最小空闲连接
                    .setMasterConnectionMinimumIdleSize(0);
            //如果密码不为空，设置集群密码
            if (!StringUtil.isEmpty(password)) {
                config.useClusterServers().setPassword(password);
            }
            client = Redisson.create(config);
//            System.out.println(config.);
            //可通过打印redisson.getConfig().toJSON().toString()来检测是否配置成功
        }
        return client;
    }
}

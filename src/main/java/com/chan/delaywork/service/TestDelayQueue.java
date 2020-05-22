package com.chan.delaywork.service;

import com.chan.delaywork.entities.Order;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author: chen
 * @date: 2020/5/12 - 21:47
 * @describe:
 */
@Component
public class TestDelayQueue {

    @Resource
    private RedissonClient redissonClient;

    @PostConstruct
    public void test() {

        RBlockingDeque<Order> orderDelayQueue = redissonClient.getBlockingDeque("order_delay");

//        RDelayedQueue<Order> delayedQueue = redissonClient.getDelayedQueue(orderDelayQueue);


        ExecutorService executorService = Executors.newSingleThreadExecutor(r -> {
            Thread thread = new Thread(r);
            thread.setName("DelayWorker");
            thread.setDaemon(true);
            return thread;
        });

        System.out.println("调度线程开始============");
        executorService.execute(() -> {
            while (true){
            try {
                Order order = orderDelayQueue.take();
                System.out.println("取到一条消息");
                System.out.println(order);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        });


    }
}

package com.chan.delaywork.service;

import com.chan.delaywork.entities.Order;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RFuture;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author: chen
 * @date: 2020/5/12 - 21:33
 * @describe:
 */
@Service
public class DelayService {
    @Resource
    private RedissonClient redissonClient;


    public void offerWork(String msg) throws InterruptedException {
        RBlockingDeque<Order> orderDelayQueue = redissonClient.getBlockingDeque("order_delay");

        RDelayedQueue<Order> delayedQueue = redissonClient.getDelayedQueue(orderDelayQueue);

//        Order order = Order.builder().id("12321").createDate(new Date()).orderName("testName").build();
        Order order = null;
        for (int i = 0; i < 10; i++) {
             order = Order.builder().id(i + "").createDate(null).orderName("testName" + i).build();
            delayedQueue.offer(order, 5, TimeUnit.SECONDS);
        }


        Thread.sleep(1000);
        boolean remove2 = delayedQueue.remove(Order.builder().id(3 + "").createDate(null).orderName("testName" + 3).build());
        System.out.println(remove2);
        RFuture<Boolean> remove = orderDelayQueue.removeFirstOccurrenceAsync(Order.builder().id(2 + "").createDate(null).orderName("testName" + 2).build());
        boolean success = remove.isSuccess();
//        orderDelayQueue.removeFirstOccurrenceAsync()
        System.out.println(success);
        boolean remove1 = orderDelayQueue.remove(order);
        System.out.println(remove1);

//        Order order = Order.builder().id(111 + "").createDate(null).orderName("testName" + 111).build();
//        delayedQueue.offer(order, 5, TimeUnit.SECONDS);
//        for (int i = 10; i < 20; i++) {
//            try {
//
//                Order order = Order.builder().id(i + "").createDate(new Date()).orderName("testName" + i).build();
//                delayedQueue.offer(order, 1, TimeUnit.MINUTES);
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        delayedQueue.destroy();


    }

}

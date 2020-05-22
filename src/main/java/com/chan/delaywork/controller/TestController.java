package com.chan.delaywork.controller;

import com.chan.delaywork.service.DelayService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: chen
 * @date: 2020/5/13 - 9:25
 * @describe:
 */
@RestController
@RequestMapping("delay/test")
public class TestController {
    @Resource
    private DelayService delayService;

    @GetMapping("")
    public String test() throws InterruptedException {
        delayService.offerWork("hello delay!!!");
        return "success";
    }
}

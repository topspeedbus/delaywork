package com.chan.delaywork.controller;

import com.chan.delaywork.aspectJ.aspcet.TestAAnnotation;
import com.chan.delaywork.service.DelayService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("v1")
    @TestAAnnotation("hello world")
    public String test1(@RequestParam String s) {
        System.out.println("i'm controller test1!!!" + s);
        return "success";
    }




}

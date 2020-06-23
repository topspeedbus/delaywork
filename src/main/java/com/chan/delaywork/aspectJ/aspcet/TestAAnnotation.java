package com.chan.delaywork.aspectJ.aspcet;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: chen
 * @date: 2020/6/23 - 11:06
 * @describe:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TestAAnnotation {
    String[] value() default {};
 }

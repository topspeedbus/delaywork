package com.chan.delaywork.aspectJ.aspcet;

import lombok.SneakyThrows;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author: chen
 * @date: 2020/6/23 - 11:01
 * @describe:
 */
@Component
@Aspect
public class TestAspectJ {
    @Pointcut("@annotation(com.chan.delaywork.aspectJ.aspcet.TestAAnnotation)")
    public void testAAnno() {

    }


    @SneakyThrows
    @Around("testAAnno()")
    public void hook(ProceedingJoinPoint point) {



        Signature signature = point.getSignature();
        String simpleName = signature.getDeclaringType().getSimpleName();
        System.out.println(simpleName);

        String name = signature.getName();
        System.out.println(name);

        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        TestAAnnotation annotation = methodSignature.getMethod().getAnnotation(TestAAnnotation.class);
        String[] value = annotation.value();
        System.out.println(value[0]);


        Object[] args = point.getArgs();
        System.out.println(args[0]);


        long l = System.currentTimeMillis();

        Object proceed = point.proceed();

        System.out.println(l - System.currentTimeMillis());

        System.out.println(proceed);
    }

}

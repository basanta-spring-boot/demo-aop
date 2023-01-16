package com.javatechie.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAdvice {

    // @Pointcut("execution(* com.javatechie.*.*.*(..))")
    // @Pointcut("within(com.javatechie..*)")
    //@Pointcut("target(com.javatechie.controller.OrderController)")
    //@Pointcut("this(com.javatechie.service.OrderService)")
    /**
     * Apply pointcut where the method starts with get with anytype of argument
     * **/
    //@Pointcut("execution(* com.javatechie.*.*.get*(..))")

    /**
     * Apply pointcut where the method starts with get with only integer of argument
     **/

    //@Pointcut("execution(* com.javatechie.*.*.get*(Integer))")

    /**
     * combine multiple pointcut with || or &&
     * **/
    @Pointcut("execution(* com.javatechie.controller.OrderController.*(..)) || execution(* com.javatechie.service.OrderService.*(..))")
    private void log() {
    }




    //  @Before(value = "execution(* com.javatechie.*.*.*(..))")
    // @Before(value = "execution(* com.javatechie.controller.*.*(..))")
    @Before("log() ")
    public void logRequest(JoinPoint joinPoint) throws JsonProcessingException {
        System.out.println("kind : "+joinPoint.getKind());
        System.out.println("target : "+joinPoint.getTarget());
        log.info(" Method Executed !  {}", joinPoint.getSignature().getName());
        log.info(" Allowed execution for {}", new ObjectMapper().writeValueAsString(joinPoint.getArgs()));
    }
}

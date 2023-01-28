package com.javatechie.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class CustomLoggingFrameworks {

    @Pointcut("execution(* com.javatechie.controller.OrderController.*(..))")
    public void logFor() {

    }

    //@Around(value = "logFor()")
    @Around("@annotation(com.javatechie.annotation.LogRequestAndResponse)")
    public Object logRequestAndResponse(ProceedingJoinPoint pjp) throws Throwable {
        log.info(" Before Execution started !  {}", pjp.getSignature());
        log.info(" Request body {}", new ObjectMapper().writeValueAsString(pjp.getArgs()));
        Object obj = pjp.proceed();
        log.info(" After Execution started !  {}", pjp.getSignature());
        log.info(" Response body {}", new ObjectMapper().writeValueAsString(obj));
        return obj;
    }
}

package com.javatechie.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogExecutionTracker {

    @Around("@annotation(com.javatechie.annotation.TrackExecutionTime)")
    public Object logRequestAndResponse(ProceedingJoinPoint pjp) throws Throwable {
        log.info(" Execution started !  {}", pjp.getSignature());
        long start = System.currentTimeMillis();
        Object obj = pjp.proceed();
        log.info("Execution ended !  {}", pjp.getSignature());
        long end = System.currentTimeMillis();
        log.info("method {} took {} ms to complete execution", pjp.getSignature(), (end - start));
        return obj;
    }
}

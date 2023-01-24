package com.javatechie.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ExceptionAlertAdvice {

    @Pointcut("execution(* com.javatechie.controller.OrderController.*(..))")
    public void alertFor() {

    }

    @AfterThrowing(value = "alertFor()", throwing = "ex")
    public void logAndAlertFailure(JoinPoint jp, Exception ex) throws JsonProcessingException {
        log.info(" Execution started !  {}", jp.getSignature());
        log.info(" Request body {}", new ObjectMapper().writeValueAsString(jp.getArgs()));
        log.info("Exception occurs {}", ex.getMessage());
        if(ex instanceof RuntimeException){
            //send alerts
            System.out.println("yes its runtime exception");
        }
    }
}

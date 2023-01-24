package com.javatechie.advice;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MetricsRegistryAdvice {

    @Autowired
    private ObservationRegistry registry;

   // @AfterReturning(value = "execution(* com.javatechie.controller.OrderController.*(..))")
    public void sendMetrics(JoinPoint joinPoint){
    log.info("metrics creation started..");
        Observation.createNotStarted(joinPoint.getSignature().getName(), registry)
                .observe(()->joinPoint.getArgs());
        log.info("metrics successfully published to actuator");
    }
}

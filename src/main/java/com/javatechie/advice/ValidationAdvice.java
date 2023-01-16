package com.javatechie.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.javatechie.util.ConsumerIdMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
@Aspect
@Slf4j
public class ValidationAdvice {

    @Autowired
    private ConsumerIdMapper mapper;

    @Pointcut("execution(* com.javatechie.*.*.save*(..)) ")
    private void validate(){

    }


    @Before("validate()")
    public void logRequest(JoinPoint joinPoint) throws JsonProcessingException {
        log.info(" Method Executed for validation  {}", joinPoint.getSignature().getName());
        String orderRequest = new ObjectMapper()
                .writeValueAsString(Arrays.stream(joinPoint.getArgs())
                        .collect(Collectors.toList()).get(0));
        final ObjectNode node = new ObjectMapper().readValue(orderRequest, ObjectNode.class);

        if (node.has("consumerId")) {
            if (!mapper.getConsumerIds().contains(node.get("consumerId").asText()))
                throw new RuntimeException("Invalid access to Application ! Missing ConsumerId as part of request !");
        }
    }
}

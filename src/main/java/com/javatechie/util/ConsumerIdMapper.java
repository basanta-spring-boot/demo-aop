package com.javatechie.util;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.UUID.*;

@Component
public class ConsumerIdMapper {


    List<String> consumerIds = null;

    @PostConstruct
    public void initializeConsumerIds() {
        consumerIds = IntStream.range(1, 10)
                .mapToObj(i -> randomUUID().toString().split("-")[0])
                .collect(Collectors.toList());
        System.out.println("List of allowed consumers : " + consumerIds);
    }

    public List<String> getConsumerIds(){
        return consumerIds;
    }
}

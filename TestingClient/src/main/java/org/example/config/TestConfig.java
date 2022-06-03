package org.example.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class TestConfig {
    @Value("${rCount}")
    private int rCount;

    @Value("${wCount}")
    private int wCount;

    @Value("${idMinBound}")
    private int idMinBound;

    @Value("${idMaxBound}")
    private int idMaxBound;
}

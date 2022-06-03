package org.example;

import org.example.config.TestConfig;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringTestApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(SpringTestApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        TestService service = (TestService) context.getBean("testService");
        TestConfig config = (TestConfig) context.getBean("testConfig");

        for (int i = 0; i < config.getRCount(); i++) {
            service.postRequest();
        }
        for (int i = 0; i < config.getWCount(); i++) {
            service.getRequest();
        }
        context.close();
    }
}

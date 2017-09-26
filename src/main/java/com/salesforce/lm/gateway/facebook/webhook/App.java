package com.salesforce.lm.gateway.facebook.webhook;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App extends SpringBootServletInitializer {
    @Bean
    public AppProperties appProperties() {
        return new AppProperties();
    }

    public static void main(String[] args) {
        new App()
            .configure(new SpringApplicationBuilder(App.class))
            .run(args);
    }
}

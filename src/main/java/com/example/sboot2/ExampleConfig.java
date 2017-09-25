package com.example.sboot2;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.glassfish.jersey.server.ResourceConfig;

@Component
public class ExampleConfig extends ResourceConfig {
    //@Autowired ExampleEndpoint exampleEndpoint;

    public ExampleConfig() {
        register(ExampleEndpoint.class);
    }
}

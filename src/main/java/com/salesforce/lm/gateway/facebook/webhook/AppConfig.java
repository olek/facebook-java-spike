package com.salesforce.lm.gateway.facebook.webhook;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.glassfish.jersey.server.ResourceConfig;

@Component
public class AppConfig extends ResourceConfig {
    public AppConfig() {
        register(Routing.class);
    }
}

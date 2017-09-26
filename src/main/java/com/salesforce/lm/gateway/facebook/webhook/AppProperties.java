package com.salesforce.lm.gateway.facebook.webhook;

import org.springframework.stereotype.Component;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Component
@ConfigurationProperties("fb.webhook")
@Validated
public class AppProperties {
    @NotNull
    private String foo;

    public String foo() {
        return foo;
    }

    public void setFoo(String value) {
        this.foo = value;
    }
}

package com.salesforce.lm.gateway.facebook.webhook;

import org.springframework.beans.factory.annotation.Value;

public class AppProperties {
    @Value( "${foo:default-bar}" )
    private String foo;

    public String foo() {
        return foo;
    }
}

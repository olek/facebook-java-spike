package com.example.sboot2;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class Example extends SpringBootServletInitializer {

	public static void main(String[] args) {
		new Example()
				.configure(new SpringApplicationBuilder(Example.class))
				.run(args);
	}

}

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

//@EnableAutoConfiguration
//public class Example {
//    public static void main(String[] args) throws Exception {
//        SpringApplication.run(Example.class, args);
//    }
//}

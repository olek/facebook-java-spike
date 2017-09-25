package com.example.sboot2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

import org.glassfish.jersey.server.ManagedAsync;

import java.util.Timer;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
@Path("/hello")
public class ExampleEndpoint {
    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(ExampleEndpoint.class);

    private static final boolean DEBUG = false;
    private static final int WAIT_TIME = 100;
    //private static final Timer TIMER = new Timer();
    private static final ScheduledExecutorService TIMER = Executors.newScheduledThreadPool(5);
    private static final ExecutorService BLOCKING_CODE_EXECUTOR = Executors.newFixedThreadPool(5000);


    private static void debug(String message) {
        if (DEBUG)
            LOG.info("DDDDDD " + message);
    }

    @GET
    public String hello() {
        debug("hello endpoint");
        return "Root Hello World from spring-boot-2!";
    }

    @GET
    @Path("sync")
    public String sync() {
        debug("sync, before sleep");
        try {
            Thread.sleep(WAIT_TIME);
            debug("sync, after sleep");
            return "Sync Hello World from spring-boot-2!";
        } catch (InterruptedException e) {
            return "Interrupted";
        }
    }

    @GET
    @Path("managed-async")
    @ManagedAsync
    public void managedAsync(@Suspended final AsyncResponse asyncResponse) {
        debug("managed-async, before sleep");
        try {
            Thread.sleep(WAIT_TIME);
            debug("managed-async, after sleep");
            asyncResponse.resume("Managed async Hello World from spring-boot-2!");
        } catch (InterruptedException e) {
            asyncResponse.resume("Interrupted");
        }
    }

    @GET
    @Path("blocking-async")
    public void blockingAsync(@Suspended final AsyncResponse asyncResponse) {
        debug("blocking-async, before sleep");
        BLOCKING_CODE_EXECUTOR.execute(() -> {
            try {
                Thread.sleep(WAIT_TIME);
                debug("blocking-async, after sleep");
                asyncResponse.resume("Blocking async Hello World from spring-boot-2!");
            } catch (InterruptedException e) {
                asyncResponse.resume("Interrupted");
            }
        });
    }

    @GET
    @Path("nio-async")
    public void scheduledAsync(@Suspended final AsyncResponse asyncResponse) {
        debug("nio-async, before sleep");
        TIMER.schedule(
                () -> {
                    debug("nio-async, after sleep");
                    asyncResponse.resume("NIO async Hello World from spring-boot-2!");
                },
                WAIT_TIME,
                TimeUnit.MILLISECONDS
                );
    }
}

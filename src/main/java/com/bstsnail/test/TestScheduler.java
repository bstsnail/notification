package com.bstsnail.test;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created with Intellij IDEA.
 * User: Robin
 * Date: 12/27/16
 */
@Component
@Configurable
@EnableScheduling
public class TestScheduler {

    //@Scheduled(cron = "*/5 * * * * *")
    public void doSomething() {
        System.out.println("Schedule the test - " + new Date());
    }
}

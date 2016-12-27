package com.bstsnail.scheduler;

import com.bstsnail.model.Event;
import com.bstsnail.queue.EventQueue;
import com.bstsnail.service.DateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created with Intellij IDEA.
 * User: Robin
 * Date: 12/27/16
 */
@Component
public class SendScheduler {
    private final Logger logger = LoggerFactory.getLogger(SendScheduler.class);
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    @Autowired
    private EventQueue queue;

    @Autowired
    private DateService dateService;

    @PostConstruct
    public void start() {
        logger.info("Start up send scheduler");

        executor.scheduleWithFixedDelay(() -> {
            logger.info("Start to send the emails");
            try {

                while (queue.size() > 0) {
                    Event e = queue.pollEvent();
                    if (dateService.shouldBeSend(e)) {
                        logger.info("This event can be send out, event=" + e);
                    }
                    else {
                        logger.info("This event still need to wait, event=" + e);
                    }
                }
            }
            catch (InterruptedException e1) {
                logger.error("Fail to poll events", e1);
            }
        }, 1, 1, TimeUnit.MINUTES);
    }
}

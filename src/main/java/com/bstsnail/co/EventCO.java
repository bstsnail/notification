package com.bstsnail.co;

import com.bstsnail.model.Event;
import com.bstsnail.model.User;
import com.bstsnail.queue.EventQueue;
import com.bstsnail.service.DateService;
import com.bstsnail.service.EventService;
import com.bstsnail.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with Intellij IDEA.
 * User: Robin
 * Date: 12/27/16
 */
@Component
@Configurable
@EnableScheduling
@Service
public class EventCO {

    private final Logger logger = LoggerFactory.getLogger(EventCO.class);

    private final ExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    @Autowired
    private DateService dateService;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private EventQueue queue;

    public void scheduleEvent(final Event event) {
        executor.submit(() -> {
            logger.info("Schedule event", "event=" + event);
            User u = userService.getUser(event.getUserId());
            if (u == null) {
                logger.warn("The user is not exist", "userId=" + event.getUserId());
                return;
            }
            if (dateService.shouldBeDeliver(u, event)) {
                logger.info("This event should be deliver", "event=" + event);
                queue.addEvent(event);
            }
        });
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void scheduleEvent() {
        logger.info("Schedule the events");
        executor.submit(() -> {
            logger.info("Start to schedule events");
            List<User> users = userService.getAllUser();

            for (User u : users) {
                List<Event> events = eventService.getEvents(u.getId());

                for (Event e : events) {
                    dateService.shouldBeDeliver(u, e);
                    logger.info("This event can be deliver", "event=" + e);
                    queue.addEvent(e);
                }
            }
        });
    }
}

package com.bstsnail.queue;

import com.bstsnail.model.Event;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created with Intellij IDEA.
 * User: Robin
 * Date: 12/27/16
 */
@Component
public class EventQueue {

    private final BlockingQueue<Event> queue = new LinkedBlockingQueue<>();

    public void addEvent(Event e) {
        queue.add(e);
    }

    public Event pollEvent() throws InterruptedException {
        return queue.take();
    }

    public int size() {
        return queue.size();
    }
}

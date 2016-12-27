package com.bstsnail.service;

import com.bstsnail.model.Event;
import com.bstsnail.model.User;

/**
 * Created with Intellij IDEA.
 * User: Robin
 * Date: 12/27/16
 */
public interface DateService {

    boolean shouldBeDeliver(User u, Event e);

    boolean shouldBeSend(Event e);
}

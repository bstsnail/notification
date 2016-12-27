package com.bstsnail.service;

import com.bstsnail.model.Event;

import java.util.List;

/**
 * Created with Intellij IDEA.
 * User: Robin
 * Date: 12/26/16
 */
public interface EventService {

    void create(Event e);

    void delete(int id);

    List<Event> getEvents(int userId);

    List<Event> getAllEvent();

    void updateEvent(int id, long date, boolean isLunar);

}

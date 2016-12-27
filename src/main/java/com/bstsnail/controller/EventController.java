package com.bstsnail.controller;

import com.bstsnail.co.EventCO;
import com.bstsnail.model.Event;
import com.bstsnail.response.Response;
import com.bstsnail.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with Intellij IDEA.
 * User: Robin
 * Date: 12/26/16
 */
@RestController
public class EventController {

    @Autowired
    EventService eventService;

    @Autowired
    EventCO eventCO;

    @RequestMapping(value = "/notify/event", method = RequestMethod.POST)
    public Response addEvent(@RequestBody Event event) {
        Response resp = new Response();
        eventService.create(event);
        eventCO.scheduleEvent(event);
        resp.setStatus(200);
        resp.setErrmsg("OK");

        return resp;
    }

    @RequestMapping(value = "/notify/event/{userId:\\d+}", method = RequestMethod.GET)
    public Response getEvent(@PathVariable("userId") int userId) {
        Response resp = new Response();
        List<Event> events = eventService.getEvents(userId);
        resp.setStatus(200);
        resp.setErrmsg("OK");
        resp.setData(events);
        return resp;
    }

    @RequestMapping(value = "/notify/event", method = RequestMethod.PUT)
    public Response updateEvent(@RequestBody Event event) {
        Response resp = new Response();
        eventService.updateEvent(event.getId(), event.getDate(), event.isLunar());
        eventCO.scheduleEvent(event);
        resp.setStatus(200);
        resp.setErrmsg("OK");
        return resp;
    }


}

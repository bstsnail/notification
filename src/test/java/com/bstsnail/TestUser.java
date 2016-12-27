package com.bstsnail;

import com.bstsnail.model.Event;
import com.bstsnail.model.User;
import com.bstsnail.service.EventService;
import com.bstsnail.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created with Intellij IDEA.
 * User: Robin
 * Date: 12/27/16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestUser {

    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    @Before
    public void setup() {
        List<User> users = userService.getAllUser();
        for (User u : users) {
            System.out.println("Deleting user - " + u);
            //userService.delete(u.getEmail());
        }
    }

    @Test
    public void test() throws Exception {
        System.out.println(userService.getUser(1));
        List<Event> events = eventService.getEvents(1);
        System.out.println(events.size());
        /*
        long month = 30 * 24 * 60 * 60 * 1000L;

        long date = System.currentTimeMillis() + month;
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(date));
        LunarCalendar lc = LunarCalendar.solar2Lunar(c);
        System.out.println(lc.getDate());
        System.out.println(lc.getMonth());
        System.out.println(lc.getLeapMonth());
        System.out.println(c.get(Calendar.MONTH));
        System.out.println(c.get(Calendar.DAY_OF_MONTH));
        System.out.println(TimeZone.getDefault());
        //Thread.sleep(100000);
        */
    }
}

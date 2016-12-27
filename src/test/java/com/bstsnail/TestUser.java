package com.bstsnail;

import com.bstsnail.model.User;
import com.bstsnail.service.UserService;
import com.bstsnail.util.LunarCalendar;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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

    @Before
    public void setup() {
        List<User> users = userService.getAllUser();
        for (User u : users) {
            System.out.println("Deleting user - " + u);
            userService.delete(u.getEmail());
        }
    }

    @Test
    public void test() throws Exception {
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
    }
}

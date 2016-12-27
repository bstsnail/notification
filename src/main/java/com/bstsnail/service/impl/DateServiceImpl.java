package com.bstsnail.service.impl;

import com.bstsnail.model.Event;
import com.bstsnail.model.User;
import com.bstsnail.service.DateService;
import com.bstsnail.util.LunarCalendar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created with Intellij IDEA.
 * User: Robin
 * Date: 12/27/16
 */
@Service
public class DateServiceImpl implements DateService {

    private Logger logger = LoggerFactory.getLogger(DateServiceImpl.class);
    @Override
    public boolean shouldBeDeliver(User u, Event e) {
        long sendBeforeEvent = e.getSendBeforeEvent();
        boolean isLunar = e.isLunar();
        long date = e.getDate() - sendBeforeEvent * 60 * 1000L;
        TimeZone timezone = TimeZone.getTimeZone(u.getTimezone());
        if (isLunar) {
            return isTheSameLunarDay(date, timezone);
        }
        else {
            return isTheSameDay(date, timezone);
        }
    }

    @Override
    public boolean shouldBeSend(Event e) {
        long date = e.getDate();
        long sendBeforeEvent = e.getSendBeforeEvent();
        logger.info(String.format("Check if this event can be send out, current=%d, date=%d, sendBeforeEvent=%d",
                    System.currentTimeMillis(), date, sendBeforeEvent));
        return System.currentTimeMillis() >= (date - sendBeforeEvent*60*1000L);
    }

    private boolean isTheSameDay(long timeStamp, TimeZone timeZone) {
        Calendar now = Calendar.getInstance(timeZone);

        Calendar c = Calendar.getInstance(timeZone);
        c.setTime(new Date(timeStamp));

        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        if (month == now.get(Calendar.MONTH) &&
                day == now.get(Calendar.DAY_OF_MONTH)) {
            return true;
        }
        return false;
    }

    private boolean isTheSameLunarDay(long timestamp, TimeZone timeZone) {
        LunarCalendar now = LunarCalendar.solar2Lunar(Calendar.getInstance(timeZone));
        Calendar c = Calendar.getInstance(timeZone);
        c.setTime(new Date(timestamp));

        LunarCalendar event = LunarCalendar.solar2Lunar(c);

        int month = event.getMonth();
        int day = event.getDate();

        if (month == now.getMonth() &&
                day == now.getDate()) {
            return true;
        }

        return false;
    }
}

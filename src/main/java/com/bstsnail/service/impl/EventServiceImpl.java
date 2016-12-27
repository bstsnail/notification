package com.bstsnail.service.impl;

import com.bstsnail.model.Event;
import com.bstsnail.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with Intellij IDEA.
 * User: Robin
 * Date: 12/26/16
 */
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public void create(Event event) {
        jdbcTemplate.update("INSERT INTO event(user_id, date, isLunar, description, emails, sendBeforeEvent, polling) VALUES(?, ?, ?, ?, ?, ?, ?)",
                            event.getUserId(),
                            event.getDate(),
                            event.isLunar(),
                            event.getDescription(),
                            event.getEmails(),
                            event.getSendBeforeEvent(),
                            event.getPollingInterval());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM event WHERE id = ?", id);
    }

    @Override
    public List<Event> getEvents(int userId) {
        return jdbcTemplate.query("SELECT * FROM event WHERE user_id=?",
                                  new Object[]{userId},
                                  (ResultSet rs, int rowNum) -> {
                                      return populateEvent(rs);
                                  }
                                  );
    }

    @Override
    public List<Event> getAllEvent() {
        return jdbcTemplate.query("SELECT * FROM event",
                                  (ResultSet rs, int rowNum) -> {
                                      return populateEvent(rs);
                                  }
                                 );
    }

    @Override
    public void updateEvent(int id, long date, boolean isLunar) {
        jdbcTemplate.update("UPDATE event SET date=?, isLunar=? WHERE id=?",
                            date, isLunar, id);
    }


    private Event populateEvent(ResultSet rs) throws SQLException {
        Event e = new Event();
        e.setId(rs.getInt("id"));
        e.setUserId(rs.getInt("user_id"));
        e.setDate(rs.getInt("date"));
        e.setLunar(rs.getBoolean("isLunar"));
        e.setDescription(rs.getString("description"));
        e.setEmails(rs.getString("emails"));
        e.setSendBeforeEvent(rs.getInt("sendBeforeEvent"));
        e.setPollingInterval(rs.getInt("polling"));
        return e;
    }
}

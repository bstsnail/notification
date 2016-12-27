package com.bstsnail.service.impl;

import com.bstsnail.model.User;
import com.bstsnail.service.UserService;
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
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(User user) {
        jdbcTemplate.update("INSERT INTO user(username, password, email, timezone) VALUES(?,?,?,?)",
                            user.getUsername(),
                            user.getPassword(),
                            user.getEmail(),
                            user.getTimezone()
                           );
    }

    @Override
    public void delete(String email) {
        jdbcTemplate.update("DELETE FROM user WHERE email=?", email);
    }

    @Override
    public User getUser(String email) {
        return jdbcTemplate.queryForObject("SELECT * FROM user WHERE email=?",
                                           new Object[]{email},
                                           this::populateUser
                                    );
    }

    @Override
    public User getUser(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM user WHERE id=?",
                                           new Object[]{id},
                                           this::populateUser
                                          );
    }

    @Override
    public List<User> getAllUser() {
        return jdbcTemplate.query("SELECT * FROM user",
                                  this::populateUser);
    }

    @Override
    public void updateUser(String username, String password, String email) {
        jdbcTemplate.update("UPDATE user SET username=?, password=? WHERE email=?",
                                   username, password, email);
    }

    private User populateUser(ResultSet rs, int rowNum) throws SQLException {
        User u = new User();
        u.setId(rs.getInt("id"));
        u.setId(rs.getInt("id"));
        u.setUsername(rs.getString("username"));
        u.setPassword(rs.getString("password"));
        u.setEmail(rs.getString("email"));
        u.setTimezone(rs.getString("timezone"));
        return u;
    }
}

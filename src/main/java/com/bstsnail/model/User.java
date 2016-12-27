package com.bstsnail.model;

import org.json.JSONObject;

import java.util.TimeZone;

/**
 * Created with Intellij IDEA.
 * User: Robin
 * Date: 12/26/16
 */
public class User {

    private int id;
    private String username;
    private String email;
    private String password;
    private String timezone = TimeZone.getDefault().getID();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("id", id)
            .put("username", username)
            .put("email", email)
            .put("timezone", timezone);
        return json.toString();
    }
}

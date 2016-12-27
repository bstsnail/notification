package com.bstsnail.model;

import org.json.JSONObject;

/**
 * Created with Intellij IDEA.
 * User: Robin
 * Date: 12/26/16
 */
public class Event {
    private int id;
    private int userId;
    private long date;
    private boolean isLunar;
    private String description;
    private String emails;
    private int sendBeforeEvent;
    private int pollingInterval;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public boolean isLunar() {
        return isLunar;
    }

    public void setLunar(boolean lunar) {
        isLunar = lunar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public int getSendBeforeEvent() {
        return sendBeforeEvent;
    }

    public void setSendBeforeEvent(int sendBeforeEvent) {
        this.sendBeforeEvent = sendBeforeEvent;
    }

    public int getPollingInterval() {
        return pollingInterval;
    }

    public void setPollingInterval(int pollingInterval) {
        this.pollingInterval = pollingInterval;
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("id", id)
            .put("userId", userId)
            .put("date", date)
            .put("isLunar", isLunar)
            .put("description", description)
            .put("emails", emails)
            .put("sendBeforeEvent", sendBeforeEvent)
            .put("pollingInterval", pollingInterval);

        return json.toString();
    }
}

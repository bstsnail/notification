package com.bstsnail.response;

import java.util.List;

/**
 * Created with Intellij IDEA.
 * User: Robin
 * Date: 12/26/16
 */
public class Response {

    private int status;
    private String errmsg;
    private List data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}

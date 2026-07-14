package com.studentlogin.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class LoginHistoryEntry {

    private int id;
    private String username;
    private Timestamp loginTime;
    private String ipAddress;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getFormattedLoginTime() {
        return loginTime == null ? "" : new SimpleDateFormat("MMM d, yyyy HH:mm").format(loginTime);
    }
}

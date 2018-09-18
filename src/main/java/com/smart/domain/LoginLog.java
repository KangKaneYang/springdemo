package com.smart.domain;

import java.io.Serializable;
import java.util.Date;

public class LoginLog implements Serializable {
    private int id;
    private String userName;
    private String ip;
    private String dateStr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    @Override
    public String toString() {
        return "LoginLog{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", ip='" + ip + '\'' +
                ", dateStr='" + dateStr + '\'' +
                '}';
    }
}

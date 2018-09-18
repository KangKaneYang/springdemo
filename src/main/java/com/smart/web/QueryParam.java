package com.smart.web;

public class QueryParam {
    private String userName;
    private String courseName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "QueryParam{" +
                "userName='" + userName + '\'' +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}

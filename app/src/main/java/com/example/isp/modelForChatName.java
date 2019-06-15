package com.example.isp;

public class modelForChatName {

    String name , ip , userID , date ;

    public modelForChatName() {

    }

    public modelForChatName(String name, String ip, String userID, String date) {
        this.name = name;
        this.ip = ip;
        this.userID = userID;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}


package com.example.isp;

public class modelForPaymentRow {


    String postId , uid , bill , name , ip  , month  ,trxID  , date  , status, userID ;

    public modelForPaymentRow(String postId, String uid, String bill, String name, String ip, String month, String trxID, String date, String status, String userID) {
        this.postId = postId;
        this.uid = uid;
        this.bill = bill;
        this.name = name;
        this.ip = ip;
        this.month = month;
        this.trxID = trxID;
        this.date = date;
        this.status = status;
        this.userID = userID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getTrxID() {
        return trxID;
    }

    public void setTrxID(String trxID) {
        this.trxID = trxID;
    }

    public modelForPaymentRow() {
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}

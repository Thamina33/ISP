package com.example.isp;

public class modelForState {

    String state , ftpLink ;



    public modelForState() {
    }

    public modelForState(String state, String ftpLink) {
        this.state = state;
        this.ftpLink = ftpLink;
    }

    public String getFtpLink() {
        return ftpLink;
    }

    public void setFtpLink(String ftpLink) {
        this.ftpLink = ftpLink;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

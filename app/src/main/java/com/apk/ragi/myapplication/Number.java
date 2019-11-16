package com.apk.ragi.myapplication;

public class Number {
    String noId;
    Integer no;

    public Number() {
    }

    public Number(String noId, Integer no) {
        this.noId = noId;
        this.no = no;
    }

    public String getNoId() {
        return noId;
    }

    public Integer getNo() {
        return no;
    }

    public void setNoId(String noId) {
        this.noId = noId;
    }

    public void setNo(Integer no) {
        this.no = no;
    }
}

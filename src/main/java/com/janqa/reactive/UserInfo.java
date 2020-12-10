package com.janqa.reactive;

public class UserInfo {
    private String name;
    private long num;

    public UserInfo(String name, long num) {
        this.name = name;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    @Override public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", num=" + num +
                '}';
    }
}

package com.janqa.reactive.model;

public class Req {
    private String index;
    private Account account;

    public Req(String index, Account account) {
        this.index = index;
        this.account = account;
    }
}

package com.janqa.reactive.model;

public class ReqDTO {
    private String index;
    private AccountDTO account;

    public ReqDTO(String index, AccountDTO account) {
        this.index = index;
        this.account = account;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }
}

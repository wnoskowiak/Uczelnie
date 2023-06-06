package com.example.uczelnie.gamelogic;

import java.util.ArrayList;

public class User {
    private String login;
    private Village village;
    private Wallet wallet;

    public User(String login, String villageName) {
        this.login = login;
        this.village = new Village(villageName, );
        this.wallet = new Wallet();
    }
    public String getLogin() {
        return this.login;
    }
}

package com.example.uczelnie.gamelogic;

import java.util.ArrayList;

public class User {
    private String login;
    private ArrayList<Building> buildings;
    private Wallet wallet;
    public User(String login) {
        this.login = login;
        this.buildings = new ArrayList<>();
        this.wallet = new Wallet();
    }
    public String login() {
        return this.login;
    }
    public ArrayList<Building> buildings() {
        return this.buildings;
    }
    public void addBuilding(Building building) {
        this.buildings.add(building);
    }

    public void buy(Building building) {
        if (!wallet.canBuy(building)) {
            // Jakiś błąd/wyjątek;
        }
        wallet.updateBalance(-building.cost());
        this.addBuilding(building);

    }
}

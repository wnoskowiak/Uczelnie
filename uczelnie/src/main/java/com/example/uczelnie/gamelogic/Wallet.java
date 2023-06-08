package com.example.uczelnie.gamelogic;

public class Wallet {
    private int accountBalance;
    public Wallet() {
        this.accountBalance = 0;
    }

    // Fajnie by było zrobić klasę/interfejs po której dziedziczą wszystkie obiekty, które można kupić.
    public boolean canBuy(Building building) {
        if (building.cost() <= this.accountBalance)
            return true;
        else
            return false;
    }

    public void updateBalance (int cost) {
        this.accountBalance += cost;
    }
}

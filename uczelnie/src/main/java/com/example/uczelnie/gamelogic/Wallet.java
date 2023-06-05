package com.example.uczelnie.gamelogic;

public class Wallet {
    private long moneyOwned;

    public long getMoney() {
        return moneyOwned;
    }

    public void deduct(long cost) {
        if (this.moneyOwned < cost)
            return;
        this.moneyOwned -= cost;
    }

    public void addMoney(long money) {
        this.moneyOwned += money;
    }
}

package com.example.uczelnie.gamelogic;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "village")

public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long moneyOwned;

    public Wallet() {
        this.moneyOwned = 0;
    }

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

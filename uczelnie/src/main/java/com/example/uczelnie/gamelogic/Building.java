package com.example.uczelnie.gamelogic;

import java.time.Duration;
import java.time.Instant;

public class Building {
    private String name;
    private long bId;
    private int upgrades[];
    private long upgradecost[];
    private double mods[];
    private double priceMod;
    private String description;
    private long students;
    private long income;
    private double avgStudentQuality;
    public Instant lastCollected;
    public long moneyCap;
    private long cost;


    public Building(Village v, String name, long cost, double priceMod) {
        this.name = name;
        this.bId = v.buildingsBuilt();
        this.upgrades = new int[2];
        this.upgradecost = new long[2];
        this.lastCollected = Instant.now();
        this.mods = new double[2];
        this.students = 20; // Jak wiadomo każdy, nawet najgorszy wydział będzie mieć jakichś kandydatów
        this.priceMod = priceMod;
        this.description = "";
        this.avgStudentQuality = v.getAvgStudentQuality();
        this.cost = cost;
    }

    public boolean canBeUpgraded(Wallet wallet, int number) {
        if (number > 1 || number < 0)
            return false;
        return wallet.getMoney() > upgradecost[number];
    }

    public double getAvgStudentQuality() {
        return this.avgStudentQuality;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getName() {
        return "" + this.name;
    }

    public void setDesc(String desc) {
        this.description = desc;
    }

    public String getDesc() {
        return "" + this.description;
    }

    public void buyUpgrade(Wallet wallet, int number) {
        if (number > 1 || number < 0)
            return;
        wallet.deduct(upgradecost[number]);
        this.upgrades[number]++;
        this.upgradecost[number] *= this.priceMod;
        if (number == 0) {
            this.income = (long) (this.income * mods[0]);
        } else {
            this.avgStudentQuality *= mods[1];
        }
    }

    public long getCurrentMoney() {
        var candidate = (long) (this.avgStudentQuality * this.income * Duration.between(this.lastCollected, Instant.now()).toMinutes());
        return Long.min(candidate, this.moneyCap);
    }

    public void collect(Wallet wallet) {
        if (Duration.between(this.lastCollected, Instant.now()).toMinutes() > 1) {
            wallet.addMoney(this.getCurrentMoney());
            this.lastCollected = Instant.now();
        }
    }

    public void raiseStudentNumber(long new_students) {
        this.students += new_students;
    }

    public void enrollNewStudents(long new_students) {
        var res = this.students * this.avgStudentQuality + new_students;
        this.students += new_students;
        this.avgStudentQuality = res / this.students;
    }

    public long getStudents() {
        return this.students;
    }

    public static Building starter(Village v) {
        return new Building(v, "Wydział Zarządzania", 0, 1.6);
    }
}


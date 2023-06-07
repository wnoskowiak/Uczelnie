package com.example.uczelnie.gamelogic;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;


public class Building {
    public static final long baseCost = 1000;
    public static final double pricemod = 1.6;
    private String name;
    private int bId;
    private final int[] upgrades;
    private final long[] upgradecost;
    private final double[] mods;
    private boolean isBeingUpgraded;
    private String description;
    private long students;
    private long income;
    private double avgStudentQuality;
    public Instant lastCollected;
    public long moneyCap;
    private final long cost;


    public Building(Village v, String name, long cost, double[] mods) {
        this.name = name;
        this.upgrades = new int[2];
        this.upgradecost = new long[2];
        this.upgradecost[0] = cost / 2;
        this.upgradecost[1] = cost / 2;
        this.isBeingUpgraded = true;
        this.lastCollected = Instant.now().plus(5, ChronoUnit.YEARS);
        this.mods = mods; // mods[0] to współczynnik zwiększania zarobków wydziału, mods[1] zwiększania jakości studentów
        this.students = 20; // Jak wiadomo każdy, nawet najgorszy wydział będzie mieć jakichś kandydatów
        this.description = "";
        this.avgStudentQuality = v.getAvgStudentQuality();
        this.cost = cost;
    }

    public boolean canBeUpgraded(Wallet wallet, int number) {
        if (number > 1 || number < 0)
            return false;
        return wallet.getMoney() > upgradecost[number];
    }

    public void init(int newId) {
        this.bId = newId;
        this.lastCollected = Instant.now().plusSeconds(120);
    }

    public int getId() {
        return this.bId;
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
        if (number > 1 || number < 0 || this.isBeingUpgraded)
            return;
        this.isBeingUpgraded = true;
        this.lastCollected = Instant.now().plusSeconds(60L * this.upgrades[number]);
        wallet.deduct(upgradecost[number]);
        this.upgrades[number]++;
        this.upgradecost[number] *= pricemod;
        if (number == 0) {
            this.income = (long) (this.income * mods[0]);
        } else {
            this.avgStudentQuality *= mods[1];
        }
    }

    public long getCurrentMoney() {
        if (this.lastCollected.isAfter(Instant.now()))
            return 0;
        var candidate = (long) (this.avgStudentQuality * this.income * Duration.between(this.lastCollected, Instant.now()).toMinutes());
        return Long.min(candidate, this.moneyCap);
    }

    public boolean collect(Wallet wallet) {
        if (this.lastCollected.isAfter(Instant.now()))
            return false;
        if (this.isBeingUpgraded) {
            this.isBeingUpgraded = false;
            this.lastCollected = Instant.now();
            return true; // Tego będziemy używać z poziomu wioski
        }
        if (Duration.between(this.lastCollected, Instant.now()).toMinutes() > 1) {
            wallet.addMoney(this.getCurrentMoney());
            this.lastCollected = Instant.now();
        }
        return false;
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
        double[] mods = new double[2];
        mods[0] = 1.03;
        mods[1] = 1.03;
        return new Building(v, "Wydział Zarządzania", baseCost, mods);
    }

    public static Building wydzialBiologii(Village v) {
        double[] mods = new double[2];
        mods[0] = 1.04;
        mods[1] = 1.08;
        return new Building(v, "Wydział Biologii", baseCost * 2, mods);
    }

    public static Building wydzialChemii(Village v) {
        double[] mods = new double[2];
        mods[0] = 1.08;
        mods[1] = 1.04;
        return new Building(v, "Wydział Chemii", baseCost * 2, mods);
    }

    public static Building wydzialPrawa(Village v) {
        double[] mods = new double[2];
        mods[0] = 1.11;
        mods[1] = 1.02;
        return new Building(v, "Wydział Prawa", baseCost * 3, mods);
    }

    public static Building wydzialGeologii(Village v) {
        double[] mods = new double[2];
        mods[0] = 1.035;
        mods[1] = 1.10;
        return new Building(v, "Wydział Geologii", baseCost * 3, mods);
    }

    public static Building wydzialKomunikacji(Village v) {
        double[] mods = new double[2];
        mods[0] = 1.07;
        mods[1] = 1.1;
        return new Building(v, "Wydział Komunikacji", baseCost * 4, mods);
    }

    public static Building wydzialMechatroniki(Village v) {
        double[] mods = new double[2];
        mods[0] = 1.7;
        mods[1] = 1.23;
        return new Building(v, "Wydział Mechatroniki", baseCost * 64, mods);
    }

    public static Building wydzialInformatyki(Village v) {
        double[] mods = new double[2];
        mods[0] = 2;
        mods[1] = 1.2;
        return new Building(v, "Wydział Informatyki", baseCost * 256, mods);
    }

    public static Building wydzialMatematyki(Village v) {
        double[] mods = new double[2];
        mods[0] = 1.2;
        mods[1] = 2;
        return new Building(v, "Wydział Matematyki", baseCost * 256, mods);
    }

    public static Building wydzialNaukPolitycznych(Village v) {
        double[] mods = new double[2];
        mods[0] = 1.5;
        mods[1] = 1.25;
        return new Building(v, "Wydział Nauk Politycznych", baseCost * 64, mods);
    }

    public static Building wydzialLingwistyki(Village v) {
        double[] mods = new double[2];
        mods[0] = 1.3;
        mods[1] = 1.3;
        return new Building(v, "Wydział Lingwistyki", baseCost * 32, mods);
    }

    public static Building wydzialFilozofii(Village v) {
        double[] mods = new double[2];
        mods[0] = 1.2;
        mods[1] = 1.2;
        return new Building(v, "Wydział Filozofii", baseCost * 16, mods);
    }

    public static Building wydzialMedycyny(Village v) {
        double[] mods = new double[2];
        mods[0] = 1.3;
        mods[1] = 1.1;
        return new Building(v, "Wydział Medycyny", baseCost * 16, mods);
    }

    public static Building wydzialPsychologii(Village v) {
        double[] mods = new double[2];
        mods[0] = 1.11;
        mods[1] = 1.15;
        return new Building(v, "Wydział Psychologii", baseCost * 10, mods);
    }

    public static Building wydzialSocjologii(Village v) {
        double[] mods = new double[2];
        mods[0] = 1.09;
        mods[1] = 1.12;
        return new Building(v, "Wydział Psychologii", baseCost * 6, mods);
    }
}



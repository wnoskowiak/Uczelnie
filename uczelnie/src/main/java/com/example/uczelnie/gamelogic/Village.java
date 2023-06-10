package com.example.uczelnie.gamelogic;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "village")

public class Village {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private final List<Building> faculties; // Lista wybudowanych budynków
    private int builders;
    private int thlvl; // Town Hall lvl = poziom uczelni = limit wybudowanych budynków
    // Ponieważ nie mamy systemu punktów doświadczenia, kolejne poziomy uczelni będą
    // do kupienia

    public int getThlvl() {
        return thlvl;
    }

    public String getName() {
        return "" + name;
    }

    public long getId() {
        return id;
    }

    public void renameVillage(String newName) {
        this.name = "" + newName;
    }

    public double getAvgStudentQuality() {
        var acc = 0;
        var accstds = 0;
        for (Building b : this.faculties) {
            accstds += b.getStudents();
            acc += b.getStudents() * b.getAvgStudentQuality();
        }
        if (accstds == 0)
            return 1;
        return (double) acc / accstds;
    }

    public List<Building> getFaculties() {
        return faculties;
    }

    public long getNumOfStudents() {
        var acc = 0;
        for (Building b : this.faculties) {
            acc += b.getStudents();
        }
        return acc;
    }

    public Village(String name, long id) {
        this.name = name;
        this.id = id;
        this.builders = 1;
        this.faculties = new ArrayList<>();
        this.faculties.add(Building.starter(this));
    }

    public boolean tryToBuild(Building b) {
        if (!this.newBuildingPermitted())
            return false;
        if (!this.builderAvailable())
            return false;
        this.builders--;
        this.faculties.add(b);
        b.init(this.buildingsBuilt());
        return true;
    }

    public int buildingsBuilt() {
        return this.faculties.size();
    }

    public void freeBuilder() {
        this.builders++;
    }

    public void lvlUp() {
        this.thlvl++;
        int sqrt = (int) Math.sqrt(this.thlvl);
        if (sqrt * sqrt == thlvl)
            this.builders++;
    }

    public void tryToCollect(int bId, Wallet w) {
        if (this.faculties.get(bId).collect(w))
            this.builders++;
    }

    public void rename(int bId, String newName, String newDesc, int flags) {
        if (flags % 2 == 0)
            this.faculties.get(bId).setName(newName);
        if (flags % 3 == 0)
            this.faculties.get(bId).setDesc(newDesc);
    }

    public boolean builderAvailable() {
        return this.builders > 0;
    }

    public boolean newBuildingPermitted() {
        return this.faculties.size() < this.thlvl;
    }
}

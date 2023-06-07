package com.example.uczelnie.gamelogic;

import java.util.ArrayList;
import java.util.List;

public class Village {
    public int id;
    private String name;
    private List<Building> faculties; // Lista wybudowanych budynków
    private int thlvl; // Town Hall lvl = poziom uczelni = limit wybudowanych budynków
    // Ponieważ nie mamy systemu punktów doświadczenia, kolejne poziomy uczelni będą do kupienia

    public int getThlvl() {
        return thlvl;
    }

    public String getName() {
        return "" + name;
    }

    public int getId() {
        return id;
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

    public Village(String name, int id) {
        this.name = name;
        this.id = id;
        this.faculties = new ArrayList<>();
        this.faculties.add(Building.starter(this));
    }

    public boolean tryToBuild(String name, long cost, double pricemod) {
        if (!this.newBuildingPermitted())
            return false;
        if (pricemod <= 1)
            return false;
        if (cost <= 0)
            return false;
        Building building = new Building(this, name, cost, pricemod);
        this.faculties.add(building);
        return true;
    }

    public int buildingsBuilt() {
        return this.faculties.size();
    }

    public void lvlUp() {
        this.thlvl++;
    }

    public boolean newBuildingPermitted() {
        return this.faculties.size() < this.thlvl;
    }
}
